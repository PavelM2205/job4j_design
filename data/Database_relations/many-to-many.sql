create table employee(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	age int
);
create table project(
	id serial primary key,
	project_name varchar(255)
);
create table employees_projects(
	id serial primary key,
	employee_id int references employee(id),
	project_id int references project(id)
);