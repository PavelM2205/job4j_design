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
	devices_id int references devices(id),
	people_id int references people(id)
);


insert into devices(name, price) values('phone', 18000.00), ('Notebook', 55300.00), ('PS5', 60000.00);
insert into people(name) values('Pavel'), ('Ivan'), ('Andrey'), ('Egor');
insert into devices_people(people_id, devices_id) values(1, 1), (1, 3), (2, 1), (2, 2), (2, 3), (3, 3), (4 , 1), (4, 2);

select avg(price) from devices;

select p.name, avg(d.price) from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.devices_id = d.id
group by p.name;

select p.name, avg(d.price) from people as p
join devices_people as dp
on dp.people_id = p.id
join devices as d
on dp.devices_id = d.id
group by p.name
having avg(d.price) > 5000;