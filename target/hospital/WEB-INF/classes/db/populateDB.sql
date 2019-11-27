DELETE
FROM patient;
DELETE
FROM surgery;
DELETE
FROM visit;
DELETE
FROM referral;
DELETE
FROM examination;
DELETE
FROM work_place;
DELETE
FROM working;
DELETE
FROM users;

ALTER SEQUENCE global_sequence
  RESTART WITH 100000;

INSERT INTO users (login, role, password)
VALUES ('Patient1', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient2', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient3', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Doctor1', 'DOCTOR', '{bcrypt}$2a$10$9.SyCKdCW.hClNNHdbbx2Ou97RrLt2Ej/itH77jxavVPNhP1q8iS2'),
       ('Admin1', 'ADMIN', '{bcrypt}$2a$10$9.SyCKdCW.hClNNHdbbx2Ou97RrLt2Ej/itH77jxavVPNhP1q8iS2'),
       ('Patient4', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient5', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient6', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient7', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient8', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient9', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient10', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient11', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient12', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W'),
       ('Patient13', 'PATIENT', '{bcrypt}$2a$10$OQ.r/Jb4x0S.nKjLWPtjduz/llyFm8bHDS7fpEaEmDHfBNMETH16W');


INSERT INTO patient (id, oms, name, address, telephone, gender, blood_group, date_of_birth)
VALUES (100000, 'кб-4200120', 'Иванов Николай Семенович', 'Казань', '8934523334', 'М', 2, '1980-05-30'),
       (100001, 'кб-4200121', 'Петров Дмитрий Валентинович', 'Москва', '8987654321', 'М', 4, '1936-05-30'),
       (100002, 'кб-4200122', 'Петелин Никонор Тихонович', 'Калининград', '8949354534', 'М', 1, '1960-05-30'),
       (100005, 'кб-3434435', 'Тодоренко Яромир Гаврилович', 'Владивосток', '8943453956', 'М', 1, '1968-04-30'),
       (100006, 'кб-6554343', 'Карпов Петр Рафаилович', 'Самара', '8435345689', 'М', 1, '1954-01-06'),
       (100007, 'кб-7892301', 'Кормалев Евгений Михайлович', 'Ухта', '8927542028', 'М', 1, '1999-01-30'),
       (100008, 'кб-9803425', 'Ворошилов Климент Ефремович', 'Пенза', '8394955929', 'М', 1, '1901-05-30'),
       (100009, 'фр-3423423', 'Умнов Василий Дементьевич', 'Суздаль', '893453459', 'М', 1, '1989-11-30'),
       (100010, 'зм-4549506', 'Янышев Валерий Геннадьевич', 'Владимир', '8595599554', 'М', 1, '1959-05-30'),
       (100011, 'кв-4245642', 'Уразаева Гульшат Виленовна', 'Тверь', '8934585934', 'Ж', 1, '1973-08-30'),
       (100012, 'ущ-4200452', 'Гилязева Лейсан Сабитовна', 'Ухта', '8945349534', 'Ж', 1, '1960-05-30'),
       (100013, 'зц-5640122', 'Каташева Наталья Георгиевна', 'Инта', '8943549539', 'Ж', 1, '1927-05-30');



INSERT INTO surgery (id, date, type, patient_id)
VALUES (0, '2015-05-30 10:00:00', 'Аппендицит', 100000),
       (4, '1984-05-30 10:00:00', 'Удаление гланд', 100006),
       (5, '2001-05-30 10:00:00', 'Ампутация фаланги безымянного пальца', 100007),
       (6, '1975-05-30 10:00:00', 'Остеосинтез ключицы', 100000),
       (7, '1984-05-30 10:00:00', 'Иссечение вен', 100000),
       (3, '2016-05-30 10:00:00', 'Шунтирование аорты', 100000),
       (1, '2018-05-30 10:00:00', 'Трепанация черепа', 100001),
       (2, '1995-05-30 10:00:00', 'Гангрена нижних конечностей', 100002);

INSERT INTO visit (id, date, patient_complaint, preliminary_diagnosis, treatment, diagnosis, patient_id)
VALUES (0, '2015-05-30 10:00:00', 'Болит ухо', 'Отит', 'Капли в уши Отипакс 3 раза в день по одной капле', 'Отек среднего уха', 100000),
       (3, '2018-05-30 10:00:00', 'Болит живот, резкие боли в правой нижней части', 'Острый панкреатит', 'Диета, исключить жирное, соленое, острое. Показано употребление большого количества жидкости.', 'Гастрит первой степени', 100000),
       (1, '2015-05-30 10:00:00', 'Головные боли, головокружение', 'Повышенное артериальное давление', 'Эналаприл при ухудшении самочувствия, прогулки на свежем воздухе', 'Повышенное АД', 100001),
       (2, '2015-05-30 10:00:00', 'Болят суставы, боли при поднятии тяжестей', 'Артрит', 'Мазь Хондроксид, витаминные добавки', 'Полиартрит', 100002);

INSERT INTO referral (id, date, type, visit_id)
VALUES (0, '2017-05-30 10:00:00', 'Общий анализ крови', 0),
       (1, '2014-05-30 10:00:00', 'Общий анализ крови', 1),
       (2, '2013-05-30 10:00:00', 'Общий анализ крови', 2);


INSERT INTO examination (id, date, result, referral_id)
VALUES (0, '2017-05-30 10:00:00', 'Глюкоза 5 мг/л, Лейкоциты 6000 ммоль, Эритроциты 10х9, Тромбоциты 2х8', 0);

INSERT INTO work_place (id, name)
VALUES (0, 'ПАО Оргсинтез'),
       (277777, 'ПАО Камаз'),
       (3, 'ООО Трикотаж'),
       (4, 'ООО Французские булки'),
       (5, 'ИП Тодоренко'),
       (6, 'ПАО Сбербанк'),
       (1, 'ПАО Рога и копыта');
INSERT INTO working (work_id, patient_id)
VALUES (0, 100000),
       (277777, 100000),
       (1, 100002);

