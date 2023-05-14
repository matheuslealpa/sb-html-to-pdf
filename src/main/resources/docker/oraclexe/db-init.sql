--**********************************
--Ajuste do NLS_CHARACTERSET
--**********************************
connect sys/oracle as sysdba;
shutdown;
startup restrict;
Alter database character set INTERNAL_USE WE8ISO8859P1;
shutdown immediate;
startup;
connect system/oracle

--**********************************
--Tuning OracleXE
--**********************************
alter system set filesystemio_options=directio scope=spfile;
alter system set disk_asynch_io=false scope=spfile;

--**********************************
--Esquema htmltopdf
--**********************************

create tablespace htmltopdf datafile '/u01/app/oracle/oradata/XE/htmltopdf01.dbf' size 100M online;
create tablespace idx_htmltopdf datafile '/u01/app/oracle/oradata/XE/idx_htmltopdf01.dbf' size 100M;
create user htmltopdf identified by htmltopdf default tablespace htmltopdf temporary tablespace temp;
grant resource to htmltopdf;
grant connect to htmltopdf;
grant create view to htmltopdf;
grant create procedure to htmltopdf;
grant create materialized view to htmltopdf;
alter user htmltopdf default role connect, resource;
exit;