INSERT INTO airline (id, name) VALUES
    (1, 'Northstar Air'),
    (2, 'Continental Connect');

INSERT INTO city (id, name, population) VALUES
    (1, 'Boston', 675647),
    (2, 'New York', 8804190),
    (3, 'Washington', 689545),
    (4, 'Chicago', 2746388),
    (5, 'Atlanta', 498715);

INSERT INTO flight_route (id, airline_id, from_city_id, to_city_id, distance_km, duration_minutes) VALUES
    (1001, 1, 1, 2, 306.0, 90),
    (1002, 1, 2, 3, 328.0, 100),
    (1003, 2, 1, 3, 634.0, 120),
    (1004, 2, 3, 4, 958.0, 150),
    (1005, 1, 2, 4, 1145.0, 180),
    (1006, 2, 4, 5, 945.0, 145);
