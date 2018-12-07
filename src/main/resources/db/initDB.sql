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
  login            VARCHAR                 NOT NULL,
  role             VARCHAR                 NOT NULL,
  password         VARCHAR                 NOT NULL
);
CREATE UNIQUE INDEX users_unique_login_idx ON users (login);

CREATE TABLE patient
(
  id               SERIAL PRIMARY KEY     not null ,
  name             VARCHAR                 NOT NULL,
  address          VARCHAR                 NOT NULL,
  telephone        VARCHAR                 NULL,
  sex              VARCHAR                 NOT NULL,
  blood_group      INTEGER                 NULL,
  FOREIGN KEY (id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE surgery
(
  id               SERIAL PRIMARY KEY     not null ,
  date             TIMESTAMP               NOT NULL,
  type             VARCHAR                 NOT NULL,
  patient_id       INTEGER                 NOT NULL,
  FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

CREATE TABLE visit
(
  id                       SERIAL PRIMARY KEY     not null ,
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
  id               SERIAL PRIMARY KEY     not null ,
  date             TIMESTAMP               NOT NULL,
  type             VARCHAR                 NOT NULL,
  visit_id         INTEGER                 NOT NULL,
  FOREIGN KEY (visit_id) REFERENCES visit(id) ON DELETE CASCADE
);

CREATE TABLE examination
(
  id               SERIAL PRIMARY KEY     not null ,
  date             TIMESTAMP               NOT NULL,
  result           VARCHAR                 NOT NULL,
  referral_id      INTEGER                 NOT NULL,
  FOREIGN KEY (referral_id) REFERENCES referral(id) ON DELETE CASCADE
);

CREATE TABLE work_place
(
  id               SERIAL PRIMARY KEY     not null ,
  name             VARCHAR                 NOT NULL
);

CREATE TABLE working
(
  work_id               INTEGER                 not null ,
  patient_id            INTEGER                 NOT NULL,
  FOREIGN KEY (work_id) REFERENCES work_place(id),
  FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);