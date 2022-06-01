-- Скрипт для пересоздания БД

DROP SCHEMA PUBLIC CASCADE;

CREATE TABLE department( Id integer, Name varchar(50));
INSERT INTO department VALUES (1,'Коммерческий');
INSERT INTO department VALUES (2,'Юридический');
INSERT INTO department VALUES (3,'Разработки');

CREATE TABLE employee( Id integer, Department integer, Name varchar(50), salary integer);
INSERT INTO employee VALUES (1,1, 'Коммерческий директор', 250000);
INSERT INTO employee VALUES (2,2, 'Главный консультант', 150000);
INSERT INTO employee VALUES (3,2, 'Консультант', 80000);
INSERT INTO employee VALUES (4,3, 'Разработчик1', 120000);
INSERT INTO employee VALUES (5,3, 'Разработчик2', 120000);
INSERT INTO employee VALUES (6,3, 'Разработчик3', 150000);
INSERT INTO employee VALUES (7,3, 'ТимЛид', 190000);

COMMIT;