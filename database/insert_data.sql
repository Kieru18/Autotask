ALTER SESSION SET NLS_DATE_FORMAT = "DD/MM/YYYY";

-- species: species_id, name, food_type, average_lifespan
INSERT INTO species VALUES (1, 'bird', 'herbivore', 2137);
INSERT INTO species VALUES (1, 'snake', 'carnivore', 4200);

commit;

-- locations: location_id, name, latitude, longitude
INSERT INTO locations VALUES (1, 'cage', 14, 88);
INSERT INTO locations VALUES (1, NULL, 13, 52);

commit;

-- animals: animal_id, name, birth_date, weight, location_id, species_id
INSERT INTO animals VALUES (1, 'Birb', '01/02/2021', 0.1, 1, 1);
INSERT INTO animals VALUES (1, 'Valor', '04/02/2022', 2.1, 1, 1);
INSERT INTO animals VALUES (1, 'Snek', '10/02/2019', 6.9, 2, 2);

commit;