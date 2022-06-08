create table type(
id serial primary key,
name varchar(255)
);

create table product(
id serial primary key,
name varchar(255),
type_id int REFERENCES type(id),
expired_date date,
price INTEGER
);

insert into type(name) values('фрукты');
insert into type(name) values('сыры');
insert into type(name) values('молоко');

insert into product(name, type_id, expired_date, price) 
values('яблоко', 1, '2000-05-05', 50);
insert into product(name, type_id, expired_date, price) 
values('плавленный', 2, '2015-05-05', 600);
insert into product(name, type_id, expired_date, price) 
values('фета', 2, '2022-05-05', 400);
insert into product(name, type_id, expired_date, price) 
values('жирное', 3, '2022-05-05', 800);

--1. Написать запрос получение всех продуктов с типом "СЫР"
 SELECT p.name "pName", b.name "pType"
 FROM product as p
 JOIN type as b ON p.type_id = b.id
 WHERE b.name = 'Сыр';
 
 --2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
 SELECT p.name
 FROM product p
 WHERE p.name like '%мороженое%';
 
 --3. Написать запрос, который выводит все продукты, срок годности которых истек
 SELECT p.name, p.expired_date
 FROM product p
 WHERE p.expired_date <= CURRENT_DATE;

--4. Написать запрос, который выводит самый дорогой продукт.
 SELECT name, price
 FROM product
 ORDER BY price DESC
 limit(1);
 
 --5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих.
 SELECT p.name "имя_типа", count(*)
 FROM type as p
 JOIN product as b ON b.type_id = p.id
 GROUP BY p.id;
 
 --6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
 SELECT p.name "имя_типа", b.name "имя_продукта"
 FROM type as p
 JOIN product as b ON b.type_id = p.id
 where p.name = 'Сыр' OR p.name = 'Молоко';
 
 --7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
 SELECT type.name, count(*)
 FROM product p JOIN TYPE ON p.type_id=type.id
 GROUP BY type.id
 HAVING count(*) < 10;
 
 --8. Вывести все продукты и их тип.
 SELECT p.name, type.name
 FROM product p
 JOIN TYPE ON p.type_id = type.id;