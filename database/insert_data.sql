ALTER SESSION SET NLS_DATE_FORMAT = "DD/MM/YYYY";


SELECT * FROM species;
-- species: species_id, name, food_type, average_lifespan
INSERT INTO species VALUES (1, 'bird', 'herbivore', 2137);
INSERT INTO species VALUES (1, 'snake', 'carnivore', 4200);

commit;

SELECT * FROM locations;
-- locations: location_id, name, latitude, longitude
INSERT INTO locations VALUES (1, 'cage', 14, 88);
INSERT INTO locations VALUES (1, NULL, 13, 52);

commit;


SELECT * FROM animals;
-- animals: animal_id, name, birth_date, weight, location_id, species_id
INSERT INTO animals VALUES (1, 'Birb', '01/02/2021', 0.1, 1, 1);
INSERT INTO animals VALUES (1, 'Valor', '04/02/2022', 2.1, 1, 1);
INSERT INTO animals VALUES (1, 'Snek', '10/02/2019', 6.9, 2, 2);

commit;

SELECT * FROM emp_status;
-- emp_status: status_id, description
INSERT INTO emp_status VALUES(1, 'available');
INSERT INTO emp_status VALUES(1, 'working');
INSERT INTO emp_status VALUES(1, 'unavailable');

commit;

SELECT * FROM positions;
-- positions: position_id, name, photo
INSERT INTO positions (name) VALUES('manager');
INSERT INTO positions (name) VALUES('worker');
INSERT INTO positions (name) VALUES('conservator of surfaces');

commit;

SELECT * FROM users;
-- users: user_id, login, password, role, mail
INSERT INTO users VALUES(1, 'magick', 'Jp2G|\/|D', 'user', 'paktof@onika.pl');

commit;

SELECT * FROM employees;
-- employees; employee_id, first_name, last_name, gender, birth_date, position_id, status_id, user_id
INSERT INTO employees VALUES(1, 'Piotr', '£uszcz', 'M', '18/03/1978', 3, 1, 1);
INSERT INTO employees VALUES(1, 'Jan', 'Kowalski', 'M', '01/02/1999', 2, 1, 2);

commit;



