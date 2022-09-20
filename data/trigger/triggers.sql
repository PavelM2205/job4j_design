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

CREATE OR REPLACE FUNCTION add_tax_after()
    RETURNS TRIGGER AS
$$
    DECLARE i INT;
    BEGIN
        FOR i IN
            SELECT id from inserted
        LOOP
            UPDATE products
            SET price = price * 1.2
            WHERE id = i;
        END LOOP;
        RETURN NULL;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_tax_after_insert
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE add_tax_after();

CREATE OR REPLACE FUNCTION add_tax_before()
    RETURNS TRIGGER AS
$$
    BEGIN
        NEW.price = NEW.price * 1.2;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER add_tax_before_insert
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE add_tax_before();

CREATE TABLE history_of_price (
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    price INT,
    date TIMESTAMP
);

CREATE OR REPLACE FUNCTION save_price_in_history()
    RETURNS TRIGGER AS
$$
    BEGIN
        INSERT INTO history_of_price (name, price, date)
        VALUES (NEW.name, NEW.price, now());
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';

CREATE TRIGGER save_price_in_history_when_insert
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE save_price_in_history();