DECLARE
  CURSOR c_sequences IS
    SELECT sequence_name
    FROM user_sequences;
BEGIN
    FOR a_sequence IN c_sequences LOOP
      EXECUTE IMMEDIATE 'DROP SEQUENCE ' || a_sequence.sequence_name;
    END LOOP;
END;
/

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
