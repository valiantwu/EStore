create tablespace devtp datafile 'devtp.dbf' size 120M autoextend on next 50M maxsize 20480M extent management local;
create user devUser identified by devPassWd default tablespace devtp;
ALTER USER devUser tablespace devtp;
GRANT dba to devUser;

create tablespace TBS_DYS datafile  'D:/Oracle_12c/app/dingyingsi/oradata/dingyingsi/TBS_DYS.ba' size 1024m reuse   autoextend on     next 100m     maxsize UNLIMITED extent management local;
CREATE TEMPORARY TABLESPACE TBS_DYS_TEMP TEMPFILE  'D:/Oracle_12c/app/dingyingsi/oradata/dingyingsi/TBS_DYS_TEMP.ba' SIZE 600M     autoextend on       next 32m       maxsize 2048m extent management local;

CREATE USER dingyingsi IDENTIFIED BY Dingyingsi1988 DEFAULT TABLESPACE TBS_DYS TEMPORARY TABLESPACE TBS_DYS_TEMP ACCOUNT UNLOCK;
GRANT CREATE TABLE TO dingyingsi;
GRANT UNLIMITED TABLESPACE TO dingyingsi;
GRANT CONNECT TO dingyingsi WITH ADMIN OPTION;
GRANT RESOURCE TO dingyingsi WITH ADMIN OPTION;
GRANT DBA TO dingyingsi;

Drop tablespace TBS_DYS;
Drop tablespace TBS_DYS_TEMP;

CREATE TRIGGER open_all_pdbs
   AFTER STARTUP
   ON DATABASE
BEGIN
   EXECUTE IMMEDIATE 'alter pluggable database all open';
END open_all_pdbs;
/

alter pluggable database pdbmyoracle open;

alter session set container = pdbmyoracle

alter session set container = CDB$ROOT;
