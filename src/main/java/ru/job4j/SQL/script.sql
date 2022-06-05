create table students(
id serial primary key,
Name varchar(255),
Age INTEGER,
Weight REAL 
);
insert into students(name, age, weight) values('Nik', 25, 85.5);
select * from students;
update students set name = 'Ben';
select * from students;
delete from students;
select * from students;