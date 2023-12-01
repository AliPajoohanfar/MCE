-- 1 ------------------------------------
create user MCE identified by mce;

-- 2 ------------------------------------
GRANT DBA TO mce WITH ADMIN OPTION;

-- 3 ------------------------------------
-- PLSQL Deveoper / Tools / Import Tables
-- C:\Oracle\XE\app\oracle\product\11.2.0\server\bin\sqlplus.exe
-- Import DB-Export.sql