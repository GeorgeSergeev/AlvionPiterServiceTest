Java: JDK 7/ JDK 8
Maven: v.3.3.9
Apache Tomcat: 7.0.x
DB: Oracle 12c

DB installation:
1. install and configure user for your DB with all required permissions.
run following scripts in that order:
- backend/etc/db/create_tables.sql
- backend/etc/db/test_app_pack_spec.sql
- backend/etc/db/test_app_pack_body.sql
- backend/etc/db/seeds.sql
- backend/etc/db/create_job.sql

Application installation:
1. edit backend/src/main/resources/config.properties with your DB settings
2. ojdbc installation
2.1 run /backend/etc/install_script/ojdbc_install.bat [.sh]

UI Configuration:
1. update url of backend server at  frontend\src\main\webapp\resources\config\config.json 


Autotests prepare

1. Chrome Driver setup
2. Download chromedriver (https://sites.google.com/a/chromium.org/chromedriver/)
3. Add chromedriver path to  system envirovement

Autotests run
1. Tests are placed  \test_task\test-task\src\test\java\ru\alvioneurope\ui\tests
2. Use maven for build 
- set source dir path
- set CommandLine: -Dit.test=<test class reference> -Dwebdriver.driver=chrome -DforkCount=0 clean integration-test serenity:aggregate
