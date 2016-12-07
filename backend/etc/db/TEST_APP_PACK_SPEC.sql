create or replace PACKAGE TEST_APP_PACK AS 

  TYPE result_row IS RECORD(
    order_id     NUMBER,
    in_string    VARCHAR2(20),
    out_string   VARCHAR2(80),
    order_creation_time VARCHAR2(24),
    order_execution_time VARCHAR2(24),
    status VARCHAR2(50));

  TYPE ping_row IS RECORD(
    order_id    NUMBER,
    status      VARCHAR2(50));
    
  TYPE test_table IS TABLE OF result_row;
  
  TYPE ping_table IS TABLE OF ping_row;
  
  TYPE number_arr IS TABLE OF NUMBER;

PROCEDURE create_order(
  p_string    IN VARCHAR2,
  p_exec_time IN VARCHAR2,
  p_order_id  OUT NUMBER
);

PROCEDURE drop_all;

PROCEDURE run_orders;

FUNCTION get_last_7_orders RETURN test_table PIPELINED;

PROCEDURE del_rec(orders varchar2);

FUNCTION get_finished_jobs RETURN ping_table PIPELINED;

END TEST_APP_PACK;
/