create or replace PACKAGE BODY TEST_APP_PACK AS

  PROCEDURE create_order(
    p_string    IN VARCHAR2,
    p_exec_time IN VARCHAR2,
    p_order_id  OUT NUMBER
  )
  AS
  BEGIN
    INSERT INTO orders (order_creation_time, job_start_time)
    VALUES (CURRENT_TIMESTAMP(0), TO_TIMESTAMP_TZ(p_exec_time,'DD-MM-YY HH24:MI:SS TZH:TZM'))
    RETURNING id INTO p_order_id;

    INSERT INTO order_parameters (order_id, param_name, param_type, param_value)
    VALUES (p_order_id, 'input_string', 'string', p_string);
  END create_order;

  PROCEDURE drop_all
  IS
  BEGIN
    DELETE FROM unreported_jobs;
    DELETE FROM order_results;
    DELETE FROM order_parameters;
    DELETE FROM orders;
  END;

  PROCEDURE run_orders AS
  BEGIN
    INSERT INTO unreported_jobs (order_id)
    SELECT o.id
    FROM orders o
      LEFT JOIN order_results ore ON o.id = ore.order_id 
      LEFT JOIN unreported_jobs uj ON o.id = uj.order_id 
    WHERE ore.order_id IS NULL AND uj.order_id IS NULL AND o.job_start_time <= CURRENT_TIMESTAMP(0);

    INSERT INTO order_results (order_id, job_status_id, job_result)
      WITH t AS (
        SELECT o.id, op.param_value, row_number() OVER (ORDER BY o.job_start_time) rown, ore.order_id, uj.order_id uioid
        FROM orders o
          LEFT JOIN unreported_jobs uj ON o.id = uj.order_id
          LEFT JOIN order_parameters op ON o.id = op.order_id
          LEFT JOIN order_results ore ON o.id = ore.order_id),
      for_insert AS (
        SELECT
          t1.id,
          LISTAGG(t2.param_value) WITHIN GROUP (ORDER BY t2.rown desc) res
        FROM t t1 INNER JOIN t t2 ON t1.rown - t2.rown BETWEEN 0 AND 3 AND t1.order_id IS NULL AND t1.uioid IS NOT NULL
        GROUP BY t1.id)
      SELECT id, 1, res FROM for_insert;
  END run_orders;

  FUNCTION get_last_7_orders RETURN test_table PIPELINED
  IS
  BEGIN
    FOR cursor_record IN (
      SELECT
        o.id,
        op.param_value in_string,
        ore.job_result out_string,
        TO_CHAR(o.order_creation_time, 'DD-MM-YY HH24:MI:SS TZH:TZM'),
        TO_CHAR(o.job_start_time, 'DD-MM-YY HH24:MI:SS TZH:TZM') order_execution_time,
        COALESCE(js.status, 'registered') status
      FROM (SELECT id, job_start_time, order_creation_time, row_number() OVER (ORDER BY job_start_time desc) rnum FROM orders) o
        LEFT JOIN order_parameters op ON o.id = op.order_id AND op.param_name = 'input_string'
        LEFT JOIN order_results ore ON o.id = ore.order_id
        LEFT JOIN job_statuses js ON js.id = ore.job_status_id
      WHERE o.rnum <= 7
      ORDER BY rnum desc) LOOP
      PIPE ROW(cursor_record);
    END LOOP;
  END;

PROCEDURE del_rec(orders varchar2)
  IS
    v_sql varchar2(2000);
  BEGIN
    v_sql := 'DELETE FROM unreported_jobs WHERE order_id IN (' || orders ||')';
    EXECUTE IMMEDIATE v_sql;
  END;


  FUNCTION get_finished_jobs RETURN ping_table PIPELINED
  IS
  BEGIN
    FOR cursor_record IN (
      SELECT ore.order_id, js.status
      FROM unreported_jobs uj
        INNER JOIN order_results ore ON uj.order_id = ore.order_id
        INNER JOIN job_statuses js ON js.id = ore.job_status_id) LOOP
      PIPE ROW(cursor_record);
    END LOOP;
  END;

END TEST_APP_PACK;
/