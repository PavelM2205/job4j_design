CREATE TRIGGER add_tax_after_insert
    AFTER INSERT
    ON products
    REFERENCING NEW TABLE AS inserted
    FOR EACH STATEMENT
    EXECUTE PROCEDURE add_tax_after();

CREATE OR REPLACE FUNCTION add_tax_after()
    RETURNS TRIGGER AS
$$
    BEGIN
        UPDATE products
        SET price = price * 1.2;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';


