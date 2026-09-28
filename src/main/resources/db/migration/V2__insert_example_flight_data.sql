INSERT INTO airline (id, name) VALUES
                                   (1, 'Wizz Air'),
                                   (2, 'Ryanair');


INSERT INTO city (id, name, population) VALUES
                                            (1, 'Budapest', 1680000),
                                            (2, 'Debrecen', 200000),
                                            (3, 'Hévíz', 4600),
                                            (4, 'Budaörs', 30000),
                                            (5, 'Bécs', 2000000),
                                            (6, 'Prága', 1400000);


INSERT INTO flight_route
(id, airline_id, from_city_id, to_city_id, distance_km, duration_minutes)
VALUES

    -- Magyarországon belüli járatok
    -- Budapest <-> Debrecen
    (1001, 1, 1, 2, 195.0, 50),
    (1002, 2, 2, 1, 195.0, 50),

    -- Budapest <-> Hévíz
    (1003, 2, 1, 3, 170.0, 45),
    (1004, 1, 3, 1, 170.0, 45),

    -- Budapest <-> Budaörs
    (1005, 1, 1, 4, 12.0, 15),
    (1006, 2, 4, 1, 12.0, 15),

    -- Debrecen <-> Hévíz
    (1007, 1, 2, 3, 300.0, 65),
    (1008, 2, 3, 2, 300.0, 65),

    -- Debrecen <-> Budaörs
    (1009, 2, 2, 4, 195.0, 50),
    (1010, 1, 4, 2, 195.0, 50),

    -- Hévíz <-> Budaörs
    (1011, 1, 3, 4, 135.0, 40),
    (1012, 2, 4, 3, 135.0, 40),


    -- Bécs <-> Budapest
    (1013, 1, 1, 5, 215.0, 50),
    (1014, 2, 5, 1, 215.0, 50),

    -- Bécs <-> Debrecen
    (1015, 2, 2, 5, 300.0, 65),
    (1016, 1, 5, 2, 300.0, 65),


    -- Prága <-> Budapest
    (1017, 1, 1, 6, 445.0, 75),
    (1018, 2, 6, 1, 75.0, 445.0),


    -- Bécs <-> Prága
    (1019, 2, 5, 6, 250.0, 55),
    (1020, 1, 6, 5, 250.0, 55);
