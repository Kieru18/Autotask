ALTER SESSION SET NLS_DATE_FORMAT = "DD/MM/YYYY";


SELECT * FROM species;
-- species: species_id, name, food_type, average_lifespan
INSERT INTO species VALUES (1, 'bird', 'herbivore', 2137);
INSERT INTO species VALUES (1, 'snake', 'carnivore', 4200);
INSERT INTO species VALUES (1, 'elephant', 'herbivore', 100000);
INSERT INTO species VALUES (1, 'lion', 'carnivore', 50000);
INSERT INTO species VALUES (1, 'monkey', 'omnivore', 69000);

commit;

SELECT * FROM locations;
-- locations: location_id, name, latitude, longitude
INSERT INTO locations VALUES (1, 'cage', 14, 88);
INSERT INTO locations VALUES (1, 'Elephant reservoir', 13, 52);
INSERT INTO locations VALUES (1, 'Cafe', 21, 37);
INSERT INTO locations VALUES (1, 'Ticket window', 99, 99);
INSERT INTO locations VALUES (1, 'Toilets', 45, 45);

commit;


SELECT * FROM animals;
-- animals: animal_id, name, birth_date, weight, location_id, species_id
INSERT INTO animals VALUES (1, 'Birb', '01/02/2021', 0.1, 1, 1);
INSERT INTO animals VALUES (1, 'Valor', '04/02/2022', 2.1, 1, 1);
INSERT INTO animals VALUES (1, 'Snek', '10/02/2019', 6.9, 5, 2);
INSERT INTO animals VALUES (1, 'Papa S³oñ', '01/09/2000', 10000.1, 2, 3);
INSERT INTO animals VALUES (1, 'George', '25/05/2020', 46, 4, 5);

commit;

SELECT * FROM emp_status;
-- emp_status: status_id, description
INSERT INTO emp_status VALUES(1, 'available');
INSERT INTO emp_status VALUES(1, 'working');
INSERT INTO emp_status VALUES(1, 'unavailable');

commit;

SELECT * FROM positions;
-- positions: position_id, name, photo
INSERT INTO positions VALUES(1, 'manager', 'mng.jpg');
INSERT INTO positions VALUES(1, 'worker', 'work.jpg');
INSERT INTO positions VALUES(1, 'conservator of surfaces', 'flatty.jpg');

commit;

SELECT * FROM users;
-- users: user_id, login, password, role, mail
INSERT INTO users VALUES(1, 'magick', 'Jp2G|\/|D', 'user', 'paktof@onika.pl');
INSERT INTO users VALUES(1, 'jkowal', 'password', 'admin', 'jkowal@uwu.com');
INSERT INTO users VALUES(1, 'bugatti', 'HUSTLE4LIFE', 'gigachad', 'andrew@hustleacademy.org');

commit;

SELECT * FROM employees;
-- employees; employee_id, first_name, last_name, gender, birth_date, position_id, status_id, user_id
INSERT INTO employees VALUES(1, 'Piotr', '£uszcz', 'M', '18/03/1978', 3, 1, 1);
INSERT INTO employees VALUES(1, 'Jan', 'Kowalski', 'M', '01/02/1999', 2, 1, 2);
INSERT INTO employees VALUES(1, 'Andrzej', 'Tatowski', 'M', '01/12/1986', 1, 3, 3); 

commit;

SELECT * FROM task_status;
-- task_status: status_id, description
INSERT INTO task_status VALUES(1, 'completed');
INSERT INTO task_status VALUES(1, 'ongoing');
INSERT INTO task_status VALUES(1, 'awaiting');
INSERT INTO task_status VALUES(1, 'on hold');

commit;

SELECT * FROM task_types;
-- task_types: type_id, name, description, base_priority, frequency
INSERT INTO task_types VALUES(1, 'Cleaning toilets', 'Toilets need to be shining as if they were new!', 1, NULL);
INSERT INTO task_types VALUES(1, 'Feed', 'Feed the animal from assignment!', 2, NULL);
INSERT INTO task_types VALUES(1, 'Vaccine', 'PREVENT THE SPREAD OF DEADLY VIRUS!', 0, NULL);
INSERT INTO task_types VALUES(1, 'Cash service', 'Hello, I like money!', 1, NULL);
INSERT INTO task_types VALUES(1, 'Supply water', 'Hey guys, did you know that in terms of water based Pokémon, Vaporeon is the coolest?', 3, NULL);

commit;



