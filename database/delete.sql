DECLARE
  CURSOR c_tables IS
    SELECT table_name
    FROM user_tables;
BEGIN
  FOR a_table IN c_tables LOOP
    EXECUTE IMMEDIATE 'DROP TABLE ' || a_table.table_name || ' CASCADE CONSTRAINTS';
  END LOOP;
END;
/
