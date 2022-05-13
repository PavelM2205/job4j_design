create table type(
	id serial primary key,
	name varchar(255)
);
create table product(
	id serial primary key,
	name varchar(255),
	expired_date date,
	price numeric,
	type_id int references type(id)
);

insert into type(name) values('сыр'), ('мороженое'), ('молоко'), ('мясо'), ('хлеб'), ('яблоки'), ('апельсины'), ('чай'), ('конфеты');

insert into product(name, type_id, price, expired_date) values('сыр плавленый', 1, 320.50, '2022-05-10'), 
('сыр российский', 1, 450.00, '2022-09-10'), ('белорусский сыр', 1, 480.50, '2022-08-15'), ('пломбир', 2, 40.00, '2022-10-20'), 
('эскимо', 2, 45.00, '2022-11-14'), ('фруктовый лед', 2, 30.00, '2022-10-24'), ('мороженое в стаканчике', 2, 55.00, '2023-01-10'), ('обезжиренное', 3, 70.00, '2022-09-05'), ('простоквашино', 3, 80.00, '2022-11-24'), ('никольское', 3, 75.50, '2022-08-10'), 
('свинина', 4, 420.00, '2022-06-15'), ('говядина', 4, 550.00, '2022-07-10'), ('курица', 4, 320.00, '2022-10-25'), 
('стейк свиной', 4, 450.00, '2022-11-18'), ('белый хлеб', 5, 30.00, '2022-06-05'), ('серый хлеб', 5, 35.00, '2022-05-08'), 
('бородинский', 5, 40.00, '2022-07-02'), ('фуши', 6, 180.00, '2022-10-15'), ('голден', 6, 220.00, '2022-11-20'), 
('гренни', 6, 250.00, '2022-12-25'), ('гала', 6, 230.00, '2022-08-20'), ('апельсины турецкие', 7, 240.00, '2023-02-10'), 
('апельсины египет', 7, 195.00, '2022-02-15'), ('черный чай', 8, 550.00, '2024-08-24'), ('зеленый чай', 8 , 400.00, '2023-11-05'), 
('каркаде', 8, 240.00, '2022-12-31'), ('чай ароматизированный', 8, 150.00, '2024-04-25'), ('васильки', 9, 180.00, '2022-12-30'), 
('ромашка', 9, 190.00, '2023-06-30'), ('мишки в лесу', 9, 200.00, '2023-11-15');
	

select p.name, p.expired_date, p.price from product as p
join type as t
on p.type_id = t.id
where t.name = 'сыр';

select name from product
where name like '%мороженое%'; 

select p.name, p.expired_date from product as p
where p.expired_date < current_date;

select p.name, p.price from product as p
where p.price = (select max(price) from product);

select t.name, count(p.name) from type as t
join product as p
on p.type_id = t.id
group by t.name;

select p.name, p.price from product as p
join type as t
on t.id = p.type_id
where t.name = 'сыр'
or t.name = 'молоко';

select t.name from type as t
join product as p 
on t.id = p.type_id
group by t.name
having count(p.name) < 10;

select p.name, t.name from product as p
join type as t
on t.id = p.type_id;













