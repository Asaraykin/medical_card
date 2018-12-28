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
  ('Patient1', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
  ('Patient2', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
  ('Patient3', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
  ('Doctor1', 'DOCTOR', '{bcrypt}$2a$10$9.SyCKdCW.hClNNHdbbx2Ou97RrLt2Ej/itH77jxavVPNhP1q8iS2'),
  ('Admin1', 'ADMIN', '{bcrypt}$2a$10$9.SyCKdCW.hClNNHdbbx2Ou97RrLt2Ej/itH77jxavVPNhP1q8iS2');

INSERT INTO patient(id, oms, name, address, telephone, gender, blood_group, date_of_birth)  VALUES
  (100000, 'кб-4200120', 'Ivanov Nikolay Semenovich', 'Kazan', '123456789', 'М', 2, '1980-05-30'),
  (100001, 'кб-4200121', 'Petrov', 'Moscow', '987654321', 'М', 4, '1936-05-30'),
  (100002, 'кб-4200122', 'Sidorov', 'Mars', '88888888', 'М', 1,'1960-05-30');

INSERT INTO surgery(id, date, type, patient_id)  VALUES
  (0, '2015-05-30 10:00:00', 'аппендицит', 100000),
  (4, '2015-05-30 10:00:00', 'аппендицит', 100000),
  (5, '2015-05-30 10:00:00', 'аппендицит', 100000),
  (6, '2015-05-30 10:00:00', 'аппендицит', 100000),
  (7, '2015-05-30 10:00:00', 'аппендицит', 100000),
  (3, '2015-05-30 10:00:00', 'аппендицит255555 5555555555 55555555555555555555 555555555 555555555555555555 5555555', 100000),
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
(0, 'ПАО Оргсинтез'),
(277777, 'ПАО Камаз'),
(1, 'ПАО Рога и копыта');
INSERT INTO working(work_id, patient_id) VALUES
(0,100000),
(277777, 100000),
(1, 100002);
