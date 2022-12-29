-- Generated by Oracle SQL Developer Data Modeler 20.2.0.167.1538
--   at:        2022-12-30 00:24:20 CET
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE animal_assignments (
    animals_animal_id  NUMBER NOT NULL,
    tasks_task_id      NUMBER NOT NULL
);

ALTER TABLE animal_assignments ADD CONSTRAINT animal_assignments_pk PRIMARY KEY ( animals_animal_id,
                                                                                  tasks_task_id );

CREATE TABLE animals (
    animal_id              NUMBER NOT NULL,
    name                   VARCHAR2(25 CHAR),
    birth_date             DATE,
    weight                 FLOAT,
    locations_location_id  NUMBER NOT NULL,
    species_species_id     NUMBER NOT NULL
);

ALTER TABLE animals ADD CONSTRAINT animals_pk PRIMARY KEY ( animal_id );

CREATE TABLE emp_assignments (
    tasks_task_id          NUMBER NOT NULL,
    employees_employee_id  NUMBER NOT NULL
);

ALTER TABLE emp_assignments ADD CONSTRAINT emp_assignments_pk PRIMARY KEY ( tasks_task_id,
                                                                            employees_employee_id );

CREATE TABLE emp_status (
    status_id    NUMBER NOT NULL,
    description  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE emp_status ADD CONSTRAINT emp_status_pk PRIMARY KEY ( status_id );

CREATE TABLE employees (
    employee_id            NUMBER NOT NULL,
    first_name             VARCHAR2(25 CHAR) NOT NULL,
    last_name              VARCHAR2(40 CHAR) NOT NULL,
    gender                 CHAR(1 CHAR),
    birth_date             DATE,
    positions_position_id  NUMBER NOT NULL,
    emp_status_status_id   NUMBER NOT NULL,
    users_user_id          NUMBER NOT NULL
);

CREATE UNIQUE INDEX employees__idx ON
    employees (
        users_user_id
    ASC );

ALTER TABLE employees ADD CONSTRAINT employees_pk PRIMARY KEY ( employee_id );

CREATE TABLE locations (
    location_id  NUMBER NOT NULL,
    name         VARCHAR2(25 CHAR),
    latitude     FLOAT,
    longitude    FLOAT
);

ALTER TABLE locations ADD CONSTRAINT locations_pk PRIMARY KEY ( location_id );

CREATE TABLE positions (
    position_id  NUMBER NOT NULL,
    name         VARCHAR2(25 CHAR) NOT NULL,
    photo        BLOB
);

ALTER TABLE positions ADD CONSTRAINT positions_pk PRIMARY KEY ( position_id );

CREATE TABLE species (
    species_id        NUMBER NOT NULL,
    name              VARCHAR2(40 CHAR) NOT NULL,
    food_type         VARCHAR2(20 CHAR) DEFAULT 'omnivore' NOT NULL,
    average_lifespan  NUMBER
);

ALTER TABLE species
    ADD CONSTRAINT foods CHECK ( food_type IN ( 'carnivore', 'herbivore', 'omnivore' ) );

ALTER TABLE species ADD CONSTRAINT species_pk PRIMARY KEY ( species_id );

CREATE TABLE task_status (
    status_id    NUMBER NOT NULL,
    description  VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE task_status ADD CONSTRAINT task_status_pk PRIMARY KEY ( status_id );

CREATE TABLE task_types (
    type_id        NUMBER NOT NULL,
    name           VARCHAR2(30 CHAR),
    description    VARCHAR2(150 CHAR),
    base_priority  NUMBER NOT NULL,
    frequency      DATE,
    tasks_task_id  NUMBER
);

ALTER TABLE task_types ADD CONSTRAINT task_types_pk PRIMARY KEY ( type_id );

CREATE TABLE tasks (
    task_id                NUMBER NOT NULL,
    description            VARCHAR2(120 CHAR),
    date_start             DATE NOT NULL,
    date_end               DATE,
    deadline               DATE,
    priority               NUMBER,
    locations_location_id  NUMBER NOT NULL,
    task_status_status_id  NUMBER NOT NULL
);

ALTER TABLE tasks ADD CONSTRAINT tasks_pk PRIMARY KEY ( task_id );

CREATE TABLE users (
    user_id   NUMBER NOT NULL,
    login     VARCHAR2(20 CHAR) NOT NULL,
    password  VARCHAR2(30 CHAR) NOT NULL,
    role      VARCHAR2(30 CHAR) NOT NULL,
    mail      VARCHAR2(30 CHAR)
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( user_id );

ALTER TABLE animal_assignments
    ADD CONSTRAINT animal_assignments_animals_fk FOREIGN KEY ( animals_animal_id )
        REFERENCES animals ( animal_id );

ALTER TABLE animal_assignments
    ADD CONSTRAINT animal_assignments_tasks_fk FOREIGN KEY ( tasks_task_id )
        REFERENCES tasks ( task_id );

ALTER TABLE animals
    ADD CONSTRAINT animals_locations_fk FOREIGN KEY ( locations_location_id )
        REFERENCES locations ( location_id );

ALTER TABLE animals
    ADD CONSTRAINT animals_species_fk FOREIGN KEY ( species_species_id )
        REFERENCES species ( species_id );

ALTER TABLE emp_assignments
    ADD CONSTRAINT emp_assignments_employees_fk FOREIGN KEY ( employees_employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE emp_assignments
    ADD CONSTRAINT emp_assignments_tasks_fk FOREIGN KEY ( tasks_task_id )
        REFERENCES tasks ( task_id );

ALTER TABLE employees
    ADD CONSTRAINT employees_emp_status_fk FOREIGN KEY ( emp_status_status_id )
        REFERENCES emp_status ( status_id );

ALTER TABLE employees
    ADD CONSTRAINT employees_positions_fk FOREIGN KEY ( positions_position_id )
        REFERENCES positions ( position_id );

ALTER TABLE employees
    ADD CONSTRAINT employees_users_fk FOREIGN KEY ( users_user_id )
        REFERENCES users ( user_id );

ALTER TABLE task_types
    ADD CONSTRAINT task_types_tasks_fk FOREIGN KEY ( tasks_task_id )
        REFERENCES tasks ( task_id );

ALTER TABLE tasks
    ADD CONSTRAINT tasks_locations_fk FOREIGN KEY ( locations_location_id )
        REFERENCES locations ( location_id );

ALTER TABLE tasks
    ADD CONSTRAINT tasks_task_status_fk FOREIGN KEY ( task_status_status_id )
        REFERENCES task_status ( status_id );



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                            12
-- CREATE INDEX                             1
-- ALTER TABLE                             25
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
