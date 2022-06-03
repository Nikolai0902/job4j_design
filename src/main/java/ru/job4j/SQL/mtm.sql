create table countrys(
     id serial primary key,
     name varchar(255)
 );
 
 create table languages(
     id serial primary key,
     name varchar(255)
 );
 
 create table countrys_languages(
     id serial primary key,
     country_id int references countrys(id),
     language_id int references languages(id)
 );



 insert into countrys(name) values ('USA');
insert into countrys(name) values ('Germany');
insert into countrys(name) values ('Russia');

insert into languages(name) values ('American');
insert into languages(name) values ('England');
insert into languages(name) values ('Russian');

insert into countrys_languages(countrys_id, languages_id) values (1, 1);
insert into countrys_languages(countrys_id, languages_id) values (1, 2);
insert into countrys_languages(countrys_id, languages_id) values (1, 3);
insert into countrys_languages(countrys_id, languages_id) values (2, 1);
insert into countrys_languages(countrys_id, languages_id) values (2, 2);
insert into countrys_languages(languages_id, languages_id) values (3, 3);