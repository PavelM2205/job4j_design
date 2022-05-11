create table role(
	id serial primary key,
	name varchar(255)
);
create table rules(
	id serial primary key,
	name varchar(255)
);
create table role_rules(
	id serial primary key,
	role_id int references role(id),
	rule_id int references rules(id)
);
create table users(
	id serial primary key,
	name varchar(255),
	role_id int references role(id)
);
create table state(
	id serial primary key,
	name varchar(255)
);
create table category(
	id serial primary key,
	name varchar(255)
);
create table item(
	id serial primary key,
	category_id int references category(id),
	state_id int references state(id),
	user_id int references users(id)
);
create table attachs(
	id serial primary key,
	file_name varchar(255),
	item_id int references item(id)
);
create table comments(
	id serial primary key,
	message varchar,
	item_id int references item(id)
);