SELECT * FROM user_sequences ;

/* delete all user-defined sequences */
DECLARE
  CURSOR c_sequences IS
    SELECT sequence_name
    FROM user_sequences;
BEGIN
    FOR a_sequence IN c_sequences LOOP
      IF a_sequence.sequence_name NOT LIKE 'ISEQ%' THEN
        EXECUTE IMMEDIATE 'DROP SEQUENCE ' || a_sequence.sequence_name;
      END IF; 
    END LOOP;
END;
/

/* delete all tables */
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

/* clear data from tables */
DECLARE
  CURSOR c_tables IS
  SELECT table_name
  FROM user_tables;
BEGIN
  FOR a_table IN c_tables LOOP
    EXECUTE IMMEDIATE 'TRUNCATE TABLE ' || a_table.table_name;
  END LOOP;
END;
/