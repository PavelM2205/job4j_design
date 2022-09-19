CREATE TRIGGER add_tax_before_insert
    BEFORE INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE add_tax_before();

CREATE OR REPLACE FUNCTION add_tax_before()
    RETURNS TRIGGER AS
$$
    BEGIN
        NEW.price = NEW.price * 1.2;
        RETURN NEW;
    END;
$$
LANGUAGE 'plpgsql';