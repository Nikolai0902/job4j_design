create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
select * from fauna;
insert into fauna (name,avg_age,discovery_date) values('fishBlue', 5000, '2002-05-06');
insert into fauna (name,avg_age,discovery_date) values('Dinosaur', 11000, '1970-05-06');
insert into fauna (name,avg_age,discovery_date) values('Lizard', 15000, null);
insert into fauna (name,avg_age,discovery_date) values('Elk', 100, '1930-05-06');
select * from fauna;
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';
select * from fauna where name like '%fish%'; 