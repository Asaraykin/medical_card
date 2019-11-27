DROP TABLE IF EXISTS patient CASCADE ;
DROP TABLE IF EXISTS surgery;
DROP TABLE IF EXISTS visit CASCADE ;
DROP TABLE IF EXISTS referral CASCADE ;
DROP TABLE IF EXISTS examination CASCADE ;
DROP TABLE IF EXISTS work_place CASCADE ;
DROP TABLE IF EXISTS working;
DROP TABLE IF EXISTS users CASCADE ;

DROP SEQUENCE IF EXISTS global_sequence;

CREATE SEQUENCE global_sequence START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_sequence'),
  login            VARCHAR(250)            NOT NULL,
  role             VARCHAR (250)                NOT NULL,
  password         VARCHAR  (250)               NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE TABLE patient
(
  id               INTEGER PRIMARY KEY     not null ,
  oms           VARCHAR                 NOT NULL,
  name             VARCHAR                 NOT NULL,
  date_of_birth    TIMESTAMP               NOT NULL,
  address          VARCHAR                 NOT NULL,
  telephone        VARCHAR(12)                 NULL,
  gender           VARCHAR (1)                NOT NULL,
  blood_group      SMALLINT                 NULL,
  FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX patient_unique_oms_idx ON patient (oms);



CREATE TABLE surgery
(
  id               SERIAL PRIMARY KEY      not null ,
  date             TIMESTAMP               NOT NULL,
  type             VARCHAR                 NOT NULL,
  patient_id       INTEGER                 NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

CREATE TABLE visit
(
  id                       SERIAL PRIMARY KEY      not null ,
  date                     TIMESTAMP               NOT NULL,
  patient_complaint        VARCHAR                 NOT NULL,
  preliminary_diagnosis    VARCHAR                 NOT NULL,
  treatment                VARCHAR                 NOT NULL,
  diagnosis                VARCHAR                 NOT NULL,
  patient_id               INTEGER                 NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

CREATE TABLE referral
(
  id               SERIAL PRIMARY KEY      not null ,
  date             TIMESTAMP               NOT NULL,
  type             VARCHAR                 NOT NULL,
  visit_id         INTEGER                 NOT NULL,
  FOREIGN KEY (visit_id) REFERENCES visit(id) ON DELETE CASCADE
);

CREATE TABLE examination
(
  id               SERIAL PRIMARY KEY      not null ,
  date             TIMESTAMP               NOT NULL,
  result           VARCHAR                 NOT NULL,
  referral_id      INTEGER                  NULL,
  FOREIGN KEY (id) REFERENCES referral(id) ON DELETE CASCADE
);

CREATE TABLE work_place
(
  id               SERIAL PRIMARY KEY      not null,
  name             VARCHAR                 NOT NULL
);

CREATE TABLE working
(
  work_id               INTEGER                 not null,
  patient_id            INTEGER                 NOT NULL,
  FOREIGN KEY (work_id) REFERENCES work_place(id) ON DELETE CASCADE,
  FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);