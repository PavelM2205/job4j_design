CREATE VIEW get_all_tickets_for_movie_Venom
AS SELECT s.name as Название_фильма, p.pos_row as Ряд, p.cell as Место,
       u.username as Имя, u.email as Почта, u.phone as Номер_телефона
FROM tickets as t
JOIN users as u
ON t.user_id = u.id
JOIN sessions as s
ON t.session_id = s.id
JOIN places as p
ON t.place_id = p.id
WHERE s.name = 'Venom';