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