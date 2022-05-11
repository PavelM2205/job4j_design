create table city_of_birth(
	id serial primary key,
	name_city varchar(255)
);
create table person(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	age int,
	city_id int references city_of_birth(id)
);