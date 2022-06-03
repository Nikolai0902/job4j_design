create table building(
    id serial primary key,
    name varchar(255)
);

create table coordinates(
    id serial primary key,
    coordinate varchar(255),
    position_id int references building(id)
);

insert into building(name) values ('factory');
insert into coordinates(coordinate, position_id) VALUES ('2222:3333', 1);

select * from coordinates;

select * from  building where id in (select id from coordinates);