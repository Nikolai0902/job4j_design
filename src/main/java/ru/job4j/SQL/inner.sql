create table medal(
    id serial primary key,
    number int
);
create table prize(
    id serial primary key,
    name varchar(255),
    medal_id int references medal(id) unique
);


insert into medal(number) values (1);
insert into medal(number) values (2);
insert into medal(number) values (3);
insert into medal(number) values (4);
insert into prize(name, medal_id) values ('Car', 1);
insert into prize(name, medal_id) values ('skates', 3);
insert into prize(name, medal_id) values ('Xbox', 2);


select * from prize inner 
join medal p 
on prize.medal_id = p.id;

select pp.name, p.number 
from prize as pp 
join medal as p 
on pp.medal_id = p.id;

select pp.name as Приз, 
p.number as Место from prize as pp 
join medal as p 
on pp.medal_id = p.id;


