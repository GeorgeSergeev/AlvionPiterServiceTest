--SELECT job_name, state, enabled FROM user_scheduler_jobs;
--EXECUTE   DBMS_SCHEDULER.DROP_JOB    (    'job1',    TRUE   )

BEGIN
DBMS_SCHEDULER.CREATE_JOB (
   job_name           =>  'job1',
   job_type           =>  'STORED_PROCEDURE',
   job_action         =>  'test_app_pack.run_orders',
   start_date         =>  SYSTIMESTAMP,
   repeat_interval    =>  'FREQ=SECONDLY;INTERVAL=3', /* every other day */
   --end_date           =>  '20-NOV-08 07.00.00 PM Australia/Sydney',
   --job_class          =>  'batch_update_jobs',
   comments           =>  'run_orders',
   enabled            => TRUE);
END;
/