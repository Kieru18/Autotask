-- Uaktualnij status pracownika w zależności od tego czy zadanie do niego przypisane jest ongoing

CREATE OR REPLACE PROCEDURE update_emp_status AS
BEGIN
    FOR r_emp IN (SELECT employee_id AS id FROM employees)
    LOOP
        UPDATE employees SET status_id = 1 WHERE employee_id = r_emp.id;
    END LOOP;
    
    FOR r_emp IN (SELECT UNIQUE(employee_id) AS id FROM emp_assignments e_a JOIN tasks t USING(task_id) WHERE t.status_id = 2)
    LOOP
        UPDATE employees SET status_id = 2 WHERE employee_id = r_emp.id;
    END LOOP;
END;


BEGIN
    update_emp_status;
END;

CREATE OR REPLACE PROCEDURE update_emp_status2(p_emp_id NUMBER, p_status_id NUMBER) AS
is_p_emp_id_valid BOOLEAN := FALSE;
is_p_status_id_valid BOOLEAN := FALSE;
BEGIN
    FOR r_emp IN (SELECT employee_id AS id FROM employees)
    LOOP
        IF r_emp.id = p_emp_id THEN
            is_p_emp_id_valid := TRUE;
        END IF;
    END LOOP;
    
    FOR r_status IN (SELECT status_id AS id FROM emp_status)
    LOOP
        IF r_status.id = p_status_id THEN
            is_p_status_id_valid := TRUE;
        END IF;
    END LOOP;
    IF is_p_emp_id_valid AND is_p_status_id_valid THEN
        UPDATE employees SET status_id = p_status_id WHERE employee_id = p_emp_id;
        dbms_output.put_line('Successfully changed employee: ' || p_emp_id || ' status to : '  || p_status_id|| ' id');
    ELSE
        dbms_output.put_line('Couldnt change employee status. Status id invalid or employee id invalid');
    END IF;
END;


CREATE OR REPLACE TRIGGER update_after_start_of_a_task
AFTER UPDATE ON tasks FOR EACH ROW
BEGIN
    IF :old.date_start IS NULL AND :new.date_start IS NOT NULL THEN
        FOR r_emp IN (SELECT employee_id AS id FROM emp_assignments WHERE task_id = :old.task_id)
        LOOP
            update_emp_status2(r_emp.id, 2);
        END LOOP;
    END IF;
END;


BEGIN
    update_emp_status;
END;


CREATE OR REPLACE PROCEDURE change_animal_enclosure(p_animal_id NUMBER, p_new_location_id   NUMBER) AS
is_loc_valid BOOLEAN := FALSE;
is_animal_valid BOOLEAN := FALSE;
BEGIN
    FOR r_loc   IN (SELECT location_id AS id FROM locations)
    LOOP
        IF r_loc.id = p_new_location_id THEN
            is_loc_valid := TRUE;
        END IF;
    END LOOP;
    
    FOR r_animal   IN (SELECT animal_id AS id FROM animals)
    LOOP
        IF r_animal.id = p_animal_id THEN
            is_animal_valid := TRUE;
            dbms_output.put_line('Animal is Valid');
        END IF;
    END LOOP;
    IF is_loc_valid AND is_animal_valid AND p_new_location_id NOT IN (3,4,5,14,15,16,17,18) THEN -- NUMBERS IN BRACKETS REPRESENT LOCATIONS NOT FOR ANIMALS, like cafe, toilets etc.
        UPDATE animals SET location_id = p_new_location_id WHERE animal_id = p_animal_id;
        dbms_output.put_line('Successfully changed animal: ' || p_animal_id || ' to location: '  || p_new_location_id);
    ELSE
        dbms_output.put_line('Couldnt change animal: '|| p_animal_id|| ' to location: ' || p_new_location_id || ' Animal_id invalid or location_id invalid');
    END IF;    
END;

DECLARE
animal_id NUMBER := 100;    -- 1,2 valid, 100 invalid
new_loc NUMBER := 10;       -- 1,2 valid, 3 is cafe ,100 invalid
BEGIN
    change_animal_enclosure(animal_id, new_loc);
END;





CREATE OR REPLACE FUNCTION calc_late_days_total
RETURN NUMBER AS
v_n_days NUMBER := 0;
BEGIN
    FOR r_task IN (SELECT date_end AS en, deadline AS dl FROM tasks)
    LOOP
        IF r_task.en IS NOT NULL AND r_task.en > r_task.dl THEN
            v_n_days := v_n_days + r_task.en - r_task.dl;
        ELSIF r_task.en IS NULL AND SYSDATE > r_task.dl THEN
            v_n_days := v_n_days + SYSDATE - r_task.dl;
        END IF;
    END LOOP;
    v_n_days := FLOOR(v_n_days);
    dbms_output.put_line('In total you have: '|| v_n_days|| ' days of being late');
    RETURN v_n_days;
END;

DECLARE
temp NUMBER;
BEGIN
    SELECT calc_late_days_total INTO temp FROM dual;
END;


CREATE OR REPLACE FUNCTION calc_number_animal_tasks(p_animal_id NUMBER)
RETURN NUMBER AS
v_n_tasks   NUMBER := 0;
BEGIN
    SELECT COUNT(task_id) INTO v_n_tasks
    FROM animal_assignments
    WHERE animal_id = p_animal_id;
RETURN v_n_tasks;
END;

DECLARE
v_id    NUMBER;
v_result NUMBER;
BEGIN
    SELECT animal_id, calc_number_animal_tasks(animal_id) INTO v_id, v_result FROM animals FETCH NEXT 1 ROWS ONLY;
    dbms_output.put_line('Animal: '|| v_id|| ' has: ' || v_result|| ' assigned tasks');
END;



