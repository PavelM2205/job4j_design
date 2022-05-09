create table persons(
	id serial primary key,
	name varchar(100),
	phone_number varchar(12),
	age smallint,
	note text
);
insert into persons(name, phone_number, age) values('Pavel',
'+79875468788', '32');
update persons set name = 'Ivan', age = '35';
delete from persons;