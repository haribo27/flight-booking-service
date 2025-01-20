CREATE TEMP TABLE temp_seat_letters (seat_letter CHAR(1));
INSERT INTO temp_seat_letters (seat_letter)
VALUES ('A'), ('B'), ('C'), ('D');

DO $$
    DECLARE
        flight RECORD;
        row_number INT;
    BEGIN
        FOR flight IN SELECT id FROM flights_schedule LOOP
                FOR row_number IN 1..30 LOOP
                        INSERT INTO seats (flight_id, seat_number, is_reserved)
                        SELECT
                            flight.id,
                            row_number || seat_letter,
                            FALSE
                        FROM temp_seat_letters;
                    END LOOP;
            END LOOP;
    END $$;

DROP TABLE IF EXISTS temp_seat_letters;