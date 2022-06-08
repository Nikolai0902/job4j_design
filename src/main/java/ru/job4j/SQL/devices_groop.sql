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