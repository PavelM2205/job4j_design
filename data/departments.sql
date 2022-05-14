create table departments(
	id serial primary key,
	name varchar(255)
);
create table employees(
	id serial primary key,
	name varchar(255),
	departments_id int references departments(id)
);

insert into departments(name) 
values('Экономики и финансов') , ('Бухгалтерского учета'), 
('Безопасности'), ('Казначество'), ('По созданию департаментов');

insert into employees(name, departments_id) 
values('Petr', 1), ('Pavel', 3), ('Ivan', 3), ('Nikolay', 2), ('Egor', 4);
insert into employees(name)
values('Andrey'), ('Maxim');

select * 
from employees as e 
left join departments as d
on e.departments_id = d.id;	

select * 
from departments as d 
right join employees as e 
on d.id = e.departments_id;

select * 
from employees as e 
full join departments as d 
on d.id = e.departments_id;

select d.name 
from departments as d 
left join employees as e
on d.id = e.departments_id
where e.departments_id is null
group by d.name;

select * 
from employees as e 
left join departments as d 
on e.departments_id = d.id;

select e.id, e.name, e.departments_id, d.id, d.name 
from departments as d 
right join employees as e 
on d.id = e.departments_id;

create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) 
values('Pavel', 'M'), ('Maria', 'F'), ('Ivan', 'M'), ('Olga', 'F');

select t1.name, t2.name 
from teens t1 
cross join teens t2
where t1.gender != t2.gender;














