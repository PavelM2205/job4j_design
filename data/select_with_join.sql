create table town(
	id serial primary key,
	name varchar(255)
);
create table post_index(
	id serial primary key,
	index decimal(6)
);
create table street(
	id serial primary key,
	name varchar(255)
);
create table citizen(
	id serial primary key,
	name varchar(255),
	town_id int references town(id),
	street_id int references street(id),
	post_index_id int references post_index(id)
);

insert into town(name) values('Москва');
insert into town(name) values('Красноярск');
insert into town(name) values('Владивосток');

insert into street(name) values('Новая басманная');
insert into street(name) values('Ленина');
insert into street(name) values('Луговая');

insert into post_index(index) values(107174);
insert into post_index(index) values(660045);
insert into post_index(index) values(690037);

insert into citizen(name, town_id, street_id, post_index_id) values('Ivan', 1, 1, 1);
insert into citizen(name, town_id, street_id, post_index_id) values('Maxim', 2, 2, 2);
insert into citizen(name, town_id, street_id, post_index_id) values('Alexey', 3, 3, 3);

select c.name, t.name from citizen as c
inner join town as t
on c.town_id = t.id;

select c.name, t.name, s.name, p.index 
from citizen as c
join town as t
on c.town_id = t.id
join street as s
on c.street_id = s.id
join post_index as p
on c.post_index_id = p.id;

select c.name as Имя, p.index as Почтовый_индекс
from citizen as c inner join post_index as p
on c.post_index_id = p.id;