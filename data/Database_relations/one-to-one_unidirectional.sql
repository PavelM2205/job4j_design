create table user_login(
	id serial primary key,
	login varchar(255)
);
create table user_data(
	id serial primary key,
	name varchar(255),
	email varchar(255),
	login_id int references user_login(id) unique
);