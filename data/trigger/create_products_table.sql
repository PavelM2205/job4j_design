CREATE TABLE products (
  id SERIAL PRIMARY KEY,
  name VARCHAR,
  producer VARCHAR,
  count INT DEFAULT 0,
  price INT
);

INSERT INTO  products (name, producer, count, price)
VALUES
    ('milk', 'Company', 3, 100), ('apple', 'CP', 5, 200),
    ('butter', 'S&T', 6, 300), ('cheese', 'PRT', 8, 400);