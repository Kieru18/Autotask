/* animal assignments */
CREATE TABLE animal_assignments (
    animal_id  NUMBER NOT NULL,
    task_id    NUMBER NOT NULL
);

ALTER TABLE animal_assignments ADD CONSTRAINT animal_assignments_pk PRIMARY KEY ( animal_id,
                                                                                  task_id );
                                                                                  
/* animals  */
CREATE TABLE animals (
    animal_id    NUMBER NOT NULL,
    name         VARCHAR2(25 CHAR),
    birth_date   DATE,
    weight       FLOAT,
    location_id  NUMBER NOT NULL,
    species_id   NUMBER NOT NULL
);

ALTER TABLE animals ADD CONSTRAINT animals_pk PRIMARY KEY ( animal_id );

CREATE SEQUENCE animals_seq
  START WITH 1  -- The first value generated by the sequence
  INCREMENT BY 1  -- Increment the value by 1 each time
  NOCACHE  -- Do not cache sequence values in memory
  NOCYCLE  -- Do not allow the sequence to cycle back to the start value
;

CREATE OR REPLACE TRIGGER animals_trg
  BEFORE INSERT ON animals
  FOR EACH ROW
BEGIN
  SELECT animals_seq.NEXTVAL INTO :new.animal_id FROM dual;
END;
/

/* employee assignments  */
CREATE TABLE emp_assignments (
    task_id      NUMBER NOT NULL,
    employee_id  NUMBER NOT NULL
);

ALTER TABLE emp_assignments ADD CONSTRAINT emp_assignments_pk PRIMARY KEY ( task_id,
                                                                            employee_id );

/* employee status */
CREATE TABLE emp_status (
    status_id    NUMBER NOT NULL,
    description  VARCHAR2(20 CHAR) NOT NULL
);

ALTER TABLE emp_status ADD CONSTRAINT emp_status_pk PRIMARY KEY ( status_id );


/* employees */
CREATE TABLE employees (
    employee_id  NUMBER NOT NULL,
    first_name   VARCHAR2(25 CHAR) NOT NULL,
    last_name    VARCHAR2(40 CHAR) NOT NULL,
    gender       CHAR(1 CHAR),
    birth_date   DATE,
    position_id  NUMBER NOT NULL,
    status_id    NUMBER NOT NULL,
    user_id      NUMBER NOT NULL
);

ALTER TABLE employees ADD CONSTRAINT employees_pk PRIMARY KEY ( employee_id );


/* locations */
CREATE TABLE locations (
    location_id  NUMBER NOT NULL,
    name         VARCHAR2(25 CHAR),
    latitude     FLOAT,
    longitude    FLOAT
);

ALTER TABLE locations ADD CONSTRAINT locations_pk PRIMARY KEY ( location_id );


/* positions */
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


/* species */
ALTER TABLE species
    ADD CONSTRAINT foods CHECK ( food_type IN ( 'carnivore', 'herbivore', 'omnivore' ) );

ALTER TABLE species ADD CONSTRAINT species_pk PRIMARY KEY ( species_id );


/* task_status */
CREATE TABLE task_status (
    status_id    NUMBER NOT NULL,
    description  VARCHAR2(30 CHAR) NOT NULL
);

ALTER TABLE task_status ADD CONSTRAINT task_status_pk PRIMARY KEY ( status_id );


/* task types */
CREATE TABLE task_types (
    type_id        NUMBER NOT NULL,
    name           VARCHAR2(30 CHAR),
    description    VARCHAR2(150 CHAR),
    base_priority  NUMBER NOT NULL,
    frequency      DATE,
    task_id        NUMBER
);

ALTER TABLE task_types ADD CONSTRAINT task_types_pk PRIMARY KEY ( type_id );


/* tasks */
CREATE TABLE tasks (
    task_id      NUMBER NOT NULL,
    description  VARCHAR2(120 CHAR),
    date_start   DATE NOT NULL,
    date_end     DATE,
    deadline     DATE,
    priority     NUMBER,
    location_id  NUMBER NOT NULL,
    status_id    NUMBER NOT NULL
);

ALTER TABLE tasks ADD CONSTRAINT tasks_pk PRIMARY KEY ( task_id );


/* users */
CREATE TABLE users (
    user_id   NUMBER NOT NULL,
    login     VARCHAR2(20 CHAR) NOT NULL,
    password  VARCHAR2(30 CHAR) NOT NULL,
    role      VARCHAR2(30 CHAR) NOT NULL,
    mail      VARCHAR2(30 CHAR)
);

ALTER TABLE users ADD CONSTRAINT users_pk PRIMARY KEY ( user_id );


-- foreign keys
ALTER TABLE animal_assignments
    ADD CONSTRAINT animal_assignments_animals_fk FOREIGN KEY ( animal_id )
        REFERENCES animals ( animal_id );

ALTER TABLE animal_assignments
    ADD CONSTRAINT animal_assignments_tasks_fk FOREIGN KEY ( task_id )
        REFERENCES tasks ( task_id );

ALTER TABLE animals
    ADD CONSTRAINT animals_locations_fk FOREIGN KEY ( location_id )
        REFERENCES locations ( location_id );

ALTER TABLE animals
    ADD CONSTRAINT animals_species_fk FOREIGN KEY ( species_id )
        REFERENCES species ( species_id );

ALTER TABLE emp_assignments
    ADD CONSTRAINT emp_assignments_employees_fk FOREIGN KEY ( employee_id )
        REFERENCES employees ( employee_id );

ALTER TABLE emp_assignments
    ADD CONSTRAINT emp_assignments_tasks_fk FOREIGN KEY ( task_id )
        REFERENCES tasks ( task_id );

ALTER TABLE employees
    ADD CONSTRAINT employees_emp_status_fk FOREIGN KEY ( status_id )
        REFERENCES emp_status ( status_id );

ALTER TABLE employees
    ADD CONSTRAINT employees_positions_fk FOREIGN KEY ( position_id )
        REFERENCES positions ( position_id );

ALTER TABLE employees
    ADD CONSTRAINT employees_users_fk FOREIGN KEY ( user_id )
        REFERENCES users ( user_id );

ALTER TABLE task_types
    ADD CONSTRAINT task_types_tasks_fk FOREIGN KEY ( task_id )
        REFERENCES tasks ( task_id );

ALTER TABLE tasks
    ADD CONSTRAINT tasks_locations_fk FOREIGN KEY ( location_id )
        REFERENCES locations ( location_id );

ALTER TABLE tasks
    ADD CONSTRAINT tasks_task_status_fk FOREIGN KEY ( status_id )
        REFERENCES task_status ( status_id );
