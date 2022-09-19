CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR,
    email VARCHAR,
    phone VARCHAR
);

INSERT INTO users (username, email, phone)
VALUES
    ('Ivan', 'ivan@mail', '2-00-00'), ('Pavel', 'pavel@mail', '3-00-00'),
    ('Sergey', 'serg@mail', '4-00-00'), ('Semen', 'semen@mail', '5-00-00');

CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

INSERT INTO sessions (name)
VALUES
    ('Taxi'), ('Men in black'), ('Superman'), ('Venom');

CREATE TABLE places (
    id SERIAL PRIMARY KEY,
    pos_row INT,
    cell INT
);

INSERT INTO places (pos_row, cell)
VALUES
    (1, 1), (1, 2), (1, 3),
    (2, 1), (2, 2), (2, 3),
    (3, 1), (3, 2), (3, 3);

CREATE TABLE tickets (
    id SERIAL PRIMARY KEY,
    session_id INT REFERENCES sessions(id),
    user_id INT REFERENCES users(id),
    place_id INT REFERENCES places(id),
    CONSTRAINT ticket_unique UNIQUE (session_id, place_id)
);

INSERT INTO tickets (session_id, user_id, place_id)
VALUES
    (1, 1, 1), (1, 2, 2), (2, 2, 3), (2, 1, 4),
    (3, 3, 5), (3, 3, 6), (3, 4, 1), (3, 1, 2),
    (4, 2, 8), (4, 3, 9), (4, 1, 2), (4, 4, 4);