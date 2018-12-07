DELETE FROM patient;
DELETE FROM surgery;
DELETE FROM visit;
DELETE FROM referral;
DELETE FROM examination;
DELETE FROM work_place;
DELETE FROM working;
DELETE FROM users;

ALTER SEQUENCE global_sequence RESTART WITH 100000;

INSERT INTO users (login, role, password) VALUES
  ('Patient1', 'patient', 'patient1'),
  ('Patient2', 'patient', 'patient2'),
  ('Patient3', 'patient', 'patient3'),
  ('Doctor1', 'doctor', 'password'),
  ('Admin1', 'admin', 'admin');

INSERT INTO patient(id, name, address, telephone, sex, blood_group)  VALUES
  (100000, 'Ivanov', 'Kazan', '123456789', 'М', 2),
  (100001, 'Petrov', 'Moscow', '987654321', 'М', 4),
  (100002, 'Sidorov', 'Mars', '88888888', 'М', 1);

INSERT INTO surgery(id, date, type, patient_id)  VALUES
  (0, '2015-05-30 10:00:00', 'аппендицит', 100000),
  (1, '2018-05-30 10:00:00', 'трепанация', 100001),
  (2, '1995-05-30 10:00:00', 'гангрена', 100002);

INSERT INTO visit(id, date, patient_complaint, preliminary_diagnosis, treatment, diagnosis, patient_id)  VALUES
(0, '2015-05-30 10:00:00', 'болит ухо', 'отит', 'капли в уши, антибиотик', 'отек среднего уха', 100000),
(3, '2018-05-30 10:00:00', 'болит ухо', 'отит', 'капли в уши, антибиотик', 'отек среднего уха', 100000),
(1, '2015-05-30 10:00:00', 'болит ухо2', 'отит2', 'капли в уши, антибиотик2', 'отек среднего уха2', 100001),
(2, '2015-05-30 10:00:00', 'болит ухо3', 'отит3', 'капли в уши, антибиотик3', 'отек среднего уха3', 100002);

INSERT INTO referral(id, date, type, visit_id) VALUES
(0, '2017-05-30 10:00:00', 'общий анализ крови', 0),
(1, '2014-05-30 10:00:00', 'общий анализ крови2', 1),
(2, '2013-05-30 10:00:00', 'общий анализ крови3', 2);

INSERT INTO examination(id, date, result, referral_id) VALUES
(0, '2017-05-30 10:00:00', 'повышен сахар', 0);

INSERT INTO work_place(id, name) VALUES
(0, 'ПАО Оргсинтез');

INSERT INTO working(work_id, patient_id) VALUES
(0,100000),
(0, 100001);
