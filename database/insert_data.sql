ALTER SESSION SET NLS_DATE_FORMAT = "DD/MM/YYYY";


SELECT * FROM species;
-- species: species_id, name, food_type, average_lifespan
INSERT INTO species VALUES (1, 'bird', 'herbivore', 2137);
INSERT INTO species VALUES (2, 'snake', 'carnivore', 4200);
INSERT INTO species VALUES (3, 'elephant', 'herbivore', 100000);
INSERT INTO species VALUES (4, 'lion', 'carnivore', 50000);
INSERT INTO species VALUES (5, 'monkey', 'omnivore', 69000);
INSERT INTO species VALUES (6, 'capybara', 'herbivore', 13580);
INSERT INTO species VALUES (7, 'penguin', 'carnivore', 9999);
INSERT INTO species VALUES (8, 'tiger', 'carnivore', 875382);
INSERT INTO species VALUES (9, 'giraffe', 'herbivore', 88992);
INSERT INTO species VALUES (10, 'panda', 'herbivore', 929292929);

commit;

SELECT * FROM locations;
-- locations: location_id, name, latitude, longitude
INSERT INTO locations VALUES (1, 'Bird cage', 14, 88);
INSERT INTO locations VALUES (2, 'Elephant reservoir', 13, 52);
INSERT INTO locations VALUES (3, 'Cafe', 21, 37);
INSERT INTO locations VALUES (4, 'Ticket window', 99, 99);
INSERT INTO locations VALUES (5, 'Toilets', 45, 45);
INSERT INTO locations VALUES (6, 'Snake reservoir', 13, 27);
INSERT INTO locations VALUES (7, 'Lion reservoir', 69, 69);
INSERT INTO locations VALUES (8, 'Monkey reservoir', 12, 69);
INSERT INTO locations VALUES (9, 'Capybara reservoir', 69, 12);
INSERT INTO locations VALUES (10, 'Penguin reservoir', 12, 12);
INSERT INTO locations VALUES (11, 'Tiger reservoir', 1, 3);
INSERT INTO locations VALUES (12, 'Giraffe reservoir', 3, 1);
INSERT INTO locations VALUES (13, 'Panda reservoir', 6, 15);
INSERT INTO locations VALUES (14, 'Gift shop', 2, 0);
INSERT INTO locations VALUES (15, 'Food court', 15, 0);
INSERT INTO locations VALUES (16, 'First aid station', 0, 20);
INSERT INTO locations VALUES (17, 'Lost and found', 25, 25);
INSERT INTO locations VALUES (18, 'Parking', 15, 45);


commit;


SELECT * FROM animals;
-- animals: animal_id, name, birth_date, weight, location_id, species_id
INSERT INTO animals VALUES (1, 'Birb', '01/02/2021', 0.1, 1, 1);
INSERT INTO animals VALUES (2, 'Valor', '04/02/2022', 2.1, 1, 1);
INSERT INTO animals VALUES (3, 'Snek', '10/02/2019', 6.9, 5, 2);
INSERT INTO animals VALUES (4, 'Papa S³oñ', '01/09/2000', 10000.1, 2, 3);
INSERT INTO animals VALUES (5, 'George', '25/05/2020', 46, 4, 5);
INSERT INTO animals VALUES (6, 'Simba', '01/02/2019', 70.5, 2, 4);
INSERT INTO animals VALUES (7, 'Nala', '01/03/2019', 62.1, 2, 4);
INSERT INTO animals VALUES (8, 'Zazu', '01/04/2019', 0.5, 2, 1);
INSERT INTO animals VALUES (9, 'Timon', '01/05/2019', 12.2, 3, 5);
INSERT INTO animals VALUES (10, 'Pumbaa', '01/06/2019', 78.4, 3, 6);
INSERT INTO animals VALUES (11, 'Rafiki', '01/07/2019', 18.1, 3, 5);
INSERT INTO animals VALUES (12, 'Scar', '01/08/2019', 60.3, 2, 4);
INSERT INTO animals VALUES (13, 'Mufasa', '01/09/2019', 90.2, 2, 3);
INSERT INTO animals VALUES (14, 'Sarabi', '01/10/2019', 75.1, 2, 3);
INSERT INTO animals VALUES (15, 'Shenzi', '01/11/2019', 35.5, 7, 4);
INSERT INTO animals VALUES (16, 'Banzai', '01/12/2019', 42.3, 7, 4);
INSERT INTO animals VALUES (17, 'Ed', '01/01/2020', 21.2, 7, 4);
INSERT INTO animals VALUES (18, 'King Louie', '01/02/2020', 89.1, 8, 5);
INSERT INTO animals VALUES (19, 'Baloo', '01/03/2020', 65.4, 8, 5);
INSERT INTO animals VALUES (20, 'Bagheera', '01/04/2020', 45.1, 8, 5);
INSERT INTO animals VALUES (21, 'Kaa', '01/05/2020', 8.2, 6, 2);

commit;

SELECT * FROM emp_status;
-- emp_status: status_id, description
INSERT INTO emp_status VALUES(1, 'available');
INSERT INTO emp_status VALUES(2, 'unavailable');

commit;

SELECT * FROM positions;
-- positions: position_id, name, photo
INSERT INTO positions VALUES(1, 'manager', 'mng.jpg');
INSERT INTO positions VALUES(2, 'worker', 'work.jpg');
INSERT INTO positions VALUES(3, 'conservator of surfaces', 'flatty.jpg');
INSERT INTO positions VALUES(4, 'Security', 'security.jpg');
INSERT INTO positions VALUES(5, 'Human resources', 'hr.jpg');
INSERT INTO positions VALUES(6, 'Marketing', 'marketing.jpg');

commit;

SELECT * FROM users;
-- users: user_id, login, password, role, mail
INSERT INTO users VALUES(1, 'magick', 'Jp2G|\/|D', 'user', 'paktof@onika.pl');
INSERT INTO users VALUES(2, 'jkowal', 'password', 'user', 'jkowal@uwu.com');
INSERT INTO users VALUES(3, 'bugatti', 'HUSTLE4LIFE', 'admin', 'andrew@hustleacademy.org');
INSERT INTO users VALUES(4, 'ebrown', 'p@ssw0rd', 'user', 'fake@imposter.sus');
INSERT INTO users VALUES(5, 'asmith', 'changeme', 'user', 'asmith@zoo.com');
INSERT INTO users VALUES(6, 'nataliej', 'securepw', 'user', 'nataliej@zoo.com');
INSERT INTO users VALUES(7, 'brianw', 'letmein', 'user', 'brianw@zoo.com');
INSERT INTO users VALUES(8, 'avaj', 'password', 'user', 'avaj@zoo.com');

commit;

SELECT * FROM employees;
-- employees; employee_id, first_name, last_name, gender, birth_date, position_id, status_id, user_id
INSERT INTO employees VALUES(1, 'Piotr', '£uszcz', 'M', '18/03/1978', 3, 1, 1);
INSERT INTO employees VALUES(2, 'Jan', 'Kowalski', 'M', '01/02/1999', 2, 1, 2);
INSERT INTO employees VALUES(3, 'Andrzej', 'Tatowski', 'M', '01/12/1986', 1, 2, 3);
INSERT INTO employees VALUES(4, 'Emily', 'Brown', 'W', '01/05/1980', 1, 1, 5);
INSERT INTO employees VALUES(5, 'Adam', 'Smith', 'M', '12/12/1970', 2, 2, 6);
INSERT INTO employees VALUES(6, 'Natalie', 'Johnson', 'W', '05/09/1990', 3, 2, 5);
INSERT INTO employees VALUES(7, 'Brian', 'Wilson', 'U', '01/05/1995', 4, 2, 7);
INSERT INTO employees VALUES(8, 'Ava', 'Johnson', 'U', '01/05/1998', 5, 1, 8);

commit;

SELECT * FROM task_status;
-- task_status: status_id, description
INSERT INTO task_status VALUES(1, 'completed');
INSERT INTO task_status VALUES(2, 'ongoing');
INSERT INTO task_status VALUES(3, 'awaiting');
INSERT INTO task_status VALUES(4, 'on hold');

commit;

SELECT * FROM task_types;
-- task_types: type_id, name, description, base_priority, frequency
INSERT INTO task_types VALUES(1, 'Cleaning toilets', 'Toilets need to be shining as if they were new!', 1, NULL);
INSERT INTO task_types VALUES(2, 'Feed', 'Feed the animal from assignment!', 2, NULL);
INSERT INTO task_types VALUES(3, 'Vaccine', 'PREVENT THE SPREAD OF DEADLY VIRUS!', 0, NULL);
INSERT INTO task_types VALUES(4, 'Cash service', 'Hello, I like money!', 1, NULL);
INSERT INTO task_types VALUES(5, 'Supply water', 'Hey guys, did you know that in terms of water based Pokémon, Vaporeon is the coolest?', 3, NULL);
INSERT INTO task_types VALUES(6, 'Animals checkup', 'Checking the health and wellbeing of animals', 3, NULL);
INSERT INTO task_types VALUES(7, 'Gardening', 'Maintaining the appearance of gardens and landscaping', 2, NULL);
INSERT INTO task_types VALUES(8, 'Event planning', 'Organizing and planning events for visitors', 3, NULL);
INSERT INTO task_types VALUES(9, 'Public relations', 'Handling interactions with the media and public', 2, NULL);

commit;



