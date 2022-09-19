CREATE TRIGGER save_price_in_history_when_insert
    AFTER INSERT
    ON products
    FOR EACH ROW
    EXECUTE PROCEDURE save_price_in_history();

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