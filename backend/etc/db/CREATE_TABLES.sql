CREATE TABLE orders(
	id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
	order_creation_time TIMESTAMP (0) WITH TIME ZONE NOT NULL,
	job_start_time TIMESTAMP (0) WITH TIME ZONE NOT NULL);

CREATE TABLE order_parameters(
	order_id NUMBER REFERENCES orders(id) NOT NULL,
	param_name VARCHAR2(50) NOT NULL,
	param_type VARCHAR2(50) NOT NULL,
	param_value VARCHAR2(100) NOT NULL,
	CONSTRAINT order_parameters_uniq UNIQUE (order_id, param_name)
  );

CREATE TABLE job_statuses(
	id NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY PRIMARY KEY,
	status VARCHAR2(50) NOT NULL UNIQUE);

CREATE TABLE order_results(
	order_id NUMBER REFERENCES orders(id),
	job_status_id NUMBER REFERENCES job_statuses(id),
	job_result VARCHAR2(80));

CREATE TABLE unreported_jobs(
	order_id NUMBER REFERENCES orders(id) NOT NULL UNIQUE);
/