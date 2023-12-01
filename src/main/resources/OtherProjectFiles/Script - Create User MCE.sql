-- 1 ------------------------------------
create user MCE identified by mce;

-- 2 ------------------------------------
GRANT DBA TO mce WITH ADMIN OPTION;

-- 3 ------------------------------------
-- Go to CLI (cmd r Bash)
-- Change Directory to DB-Export.sql Directory.ALLOCATE
sqlplus mce/mce
@DB-Export.sql