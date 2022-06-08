create table departments(
id serial primary key,
name varchar(255)
);

create table employees(
id serial primary key,
name varchar(255),
departments_id int REFERENCES departments(id)
);

insert into departments(name) values('Разработка');
insert into departments(name) values('Кадры');
insert into departments(name) values('Менеджмент');
insert into departments(name) values('Аудит');

insert into employees(name, departments_id) values('Mike', 1);
insert into employees(name, departments_id) values('Nik', 1);
insert into employees(name, departments_id) values('Any', 2);
insert into employees(name, departments_id) values('Ben', 3);
insert into employees(name, departments_id) values('Kris', null);

-- 2. Выполнить запросы с left, rigth, full, cross соединениями
select * from departments o left join employees d on d.departments_id = o.id;
select * from employees d left join departments o on d.departments_id = o.id;
select * from departments o right join employees d on d.departments_id = o.id;
select * from employees d right join departments o on d.departments_id = o.id;
select * from departments o full join employees d on d.departments_id = o.id;
select * from departments d cross join employees o;

--3. Используя left join найти департаменты, у которых нет работников
select * from departments o left join employees d on d.departments_id = o.id
where d.id is null;

--4. Используя left и right join написать запросы, 
--которые давали бы одинаковый результат 
--(порядок вывода колонок в эти запросах также должен быть идентичный). 
SELECT d.name, e.name FROM employees e LEFT JOIN departments d ON d.id = e.departments_id;
SELECT d.name, e.name FROM departments d RIGHT JOIN employees e ON d.id = e.departments_id;


--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
--Используя cross join составить все возможные разнополые пары

create table teens(
id serial primary key,
name varchar(255),
gender varchar(255)
);

insert into teens(name, gender) values('Nik', 'М');
insert into teens(name, gender) values('Ken', 'М');
insert into teens(name, gender) values('Men', 'М');
insert into teens(name, gender) values('Any', 'Ж');
insert into teens(name, gender) values('Vik', 'Ж');
insert into teens(name, gender) values('Len', 'Ж');

select n1.name as М, n2.name as Ж 
from teens n1 cross join teens n2
where n1.gender != n2.gender;

