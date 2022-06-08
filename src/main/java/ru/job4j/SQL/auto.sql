CREATE TABLE body(
id serial PRIMARY KEY,
name varchar(100)
);

CREATE TABLE engine(
id serial PRIMARY KEY,
name varchar(100)
);

CREATE TABLE transmission(
id serial PRIMARY KEY,
name varchar(100)
);

CREATE TABLE car(
id serial PRIMARY KEY,
name varchar(255),
body_id int REFERENCES body(id),
engine_id int REFERENCES engine(id),
transmission_id int REFERENCES transmission(id)
);

INSERT INTO body(name) VALUES('пикап');
INSERT INTO body(name) VALUES('паркетник');
INSERT INTO body(name) VALUES('хэчбэк');
INSERT INTO body(name) VALUES('седан');
INSERT INTO body(name) VALUES('самосвал');

INSERT INTO engine(name) VALUES('дизель 2.0');
INSERT INTO engine(name) VALUES('бензин 1.5');
INSERT INTO engine(name) VALUES('турбо-дизель');
INSERT INTO engine(name) VALUES('электро');
INSERT INTO engine(name) VALUES('гибрид');

INSERT INTO transmission(name) VALUES('автомат 5ст.');
INSERT INTO transmission(name) VALUES('робот 6ст.');
INSERT INTO transmission(name) VALUES('механика 4ст.');
INSERT INTO transmission(name) VALUES('механика 5ст.');
INSERT INTO transmission(name) VALUES('вариатор');

INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES('Тоета', 1, 1, 2);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES('БМВ', 2, 3, 3);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES('Мэрс', 3, 2, 4);
INSERT INTO car(name, body_id, engine_id, transmission_id) VALUES('Сузуки', 4, 3, 1);

--Вывести список всех машин и все привязанные к ним детали.
SELECT c.name "марка",b.name "кузов", e.name "двигатель", t.name "КПП" 
FROM car c
JOIN body b ON c.body_id = b.id
JOIN engine e ON c.engine_id = e.id
JOIN transmission t ON c.transmission_id = t.id;

--Вывести отдельно детали (1 деталь - 1 запрос), которые не используются в машине.
SELECT b.name "кузов" FROM body b
LEFT JOIN car c ON b.id = c.body_id
WHERE c.body_id IS NULL;

SELECT e.name "двигатель" FROM engine e
LEFT JOIN car c ON e.id = c.body_id
WHERE c.body_id IS NULL;

SELECT t.name "КПП" FROM transmission t
LEFT JOIN car c ON t.id = c.body_id
WHERE c.body_id IS NULL;


