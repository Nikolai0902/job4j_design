create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iphone', 10000);
insert into devices(name, price) values ('nokia', 80000);
insert into devices(name, price) values ('fly', 40000);

insert into people(name) values ('Mike');
insert into people(name) values ('Nik');
insert into people(name) values ('Ben');

insert into devices_people(device_id, people_id) values (2, 3);
insert into devices_people(device_id, people_id) values (1, 2);
insert into devices_people(device_id, people_id) values (3, 1);

--Используя агрегатные функции вывести среднюю цену устройств.
select avg(price) from devices;

--Используя группировку вывести для каждого человека среднюю цену его устройств.
select s.name Имя, avg(ss.price)  СрЦена
from devices as ss join devices_people as dp on ss.id= dp.device_id 
join people as s on s.id = dp.people_id
group by s.name;

--Дополнить запрос условием, что средняя стоимость устройств должна быть больше 5000.
select s.name Имя, avg(ss.price)  СрЦена
from devices as ss join devices_people as dp on ss.id= dp.device_id 
join people as s on s.id = dp.people_id
group by s.name
having avg(ss.price)>5000;