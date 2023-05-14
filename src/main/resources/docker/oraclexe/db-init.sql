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
--Esquema avaliacao
--**********************************

create tablespace avaliacao datafile '/u01/app/oracle/oradata/XE/avaliacao01.dbf' size 100M online;
create tablespace idx_avaliacao datafile '/u01/app/oracle/oradata/XE/idx_avaliacao01.dbf' size 100M;
create user avaliacao identified by avaliacao default tablespace avaliacao temporary tablespace temp;
grant resource to avaliacao;
grant connect to avaliacao;
grant create view to avaliacao;
grant create procedure to avaliacao;
grant create materialized view to avaliacao;
alter user avaliacao default role connect, resource;
exit;