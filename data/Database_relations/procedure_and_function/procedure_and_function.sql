CREATE OR REPLACE PROCEDURE insert_data(i_name VARCHAR, prod VARCHAR, i_count INT,
    i_price INT)
LANGUAGE plpgsql
AS
$$
    BEGIN
        INSERT INTO products (name, producer, count, price)
        VALUES (i_name, prod, i_count, i_price);
    END;
$$

CREATE OR REPLACE PROCEDURE update_data(u_count INT, tax FLOAT, u_id INT)
LANGUAGE plpgsql
AS
$$
    BEGIN
        IF u_count > 0 THEN
            UPDATE products SET count = count - u_count
            WHERE id = u_id;
        END IF;
        IF tax > 0 THEN
        UPDATE products SET price = price + price * tax;
        END IF;
    END;
$$

CREATE OR REPLACE FUNCTION f_insert_data(i_name VARCHAR, prod VARCHAR, i_count INT,
    i_price INT)
RETURNS VOID
AS
    $$
        BEGIN
            INSERT INTO products (name, producer, count, price)
            VALUES (i_name, prod, i_count, i_price);
        END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION f_update_data(u_count INT, tax FLOAT, u_id INT)
RETURNS INT
AS
    $$
    DECLARE result INT;
    BEGIN
        IF u_count > 0 THEN
            UPDATE products SET count = count - u_count
            WHERE id = u_id;
            SELECT INTO result count FROM products WHERE id = u_id;
        END IF;
        IF tax > 0 THEN
            UPDATE products SET price = price + price * tax;
            SELECT INTO result sum(price) from products;
        END IF;
        RETURN result;
    END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE PROCEDURE delete_data_by_id(d_id INT)
AS
    $$
        BEGIN
            DELETE FROM products WHERE id = d_id;
        END;
    $$
LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION f_delete_data_by_id(d_id INT)
    RETURNS VOID
AS
    $$
        BEGIN
            DELETE FROM products WHERE id = d_id;
        END;
    $$
LANGUAGE plpgsql;