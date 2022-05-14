create table brand(
	id serial primary key,
	name varchar(255)
);
create table body(
	id serial primary key,
	name varchar(255)
);
create table engine(
	id serial primary key,
	name varchar(255)
);
create table gear_box(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	model varchar(255),
	brand_id int references brand(id),
	body_id int references body(id),
	engine_id int references engine(id),
	gear_box_id int references gear_box(id)
);

insert into brand(name) 
values('Toyota'), ('Volkswagen'), ('BMW');

insert into body(name) 
values('sedan'), ('liftback'), ('hatchback');

insert into engine(name) 
values('KM6_2.0L'), ('NT73_3.5L'), 
('HGD_2.5L'), ('TYG_1.8L'), ('FDR_2.2L');

insert into gear_box(name) 
values('AT_6'), ('MT_5'), ('AT_7'), ('AT_5'), 
('MT_6'), ('AT_8');

insert into car(model, brand_id, body_id, 
engine_id, gear_box_id) 
values('Camry', 1, 1, 3, 1), ('Polo', 1, 2, 1, 5), ('118i', 3, 3, 3, 3);

insert into car(model, brand_id, gear_box_id)
values('RAV-4', 1, 1);

select bd.name as Марка, c.model as Модель, 
b.name as Кузов, e.name as Двигатель, gb.name as КПП
from car as c
left join brand as bd 
on c.brand_id = bd.id
left join body as b 
on c.body_id = b.id
left join engine as e 
on e.id = c.engine_id
left join gear_box as gb 
on gb.id = c.gear_box_id;	

select name 
from brand as b 
left join car as c
on b.id = c.brand_id
where c.brand_id is null;

select b.name 
from body as b 
left join car as c
on b.id = c.body_id
where c.body_id is null;

select e.name 
from engine as e 
left join car as c
on e.id = c.engine_id
where c.engine_id is null;

select gb.name 
from gear_box as gb 
left join car as c
on c.gear_box_id = gb.id
where c.gear_box_id is null;











