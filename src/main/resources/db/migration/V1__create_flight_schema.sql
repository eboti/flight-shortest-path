CREATE TABLE airline (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE city (
    id BIGINT NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    population INTEGER NOT NULL
);

CREATE TABLE flight_route (
    id BIGINT NOT NULL PRIMARY KEY,
    airline_id BIGINT,
    from_city_id BIGINT,
    to_city_id BIGINT,
    distance_km INTEGER,
    duration_minutes INTEGER,
    CONSTRAINT fk_flight_route_airline FOREIGN KEY (airline_id) REFERENCES airline (id),
    CONSTRAINT fk_flight_route_from_city FOREIGN KEY (from_city_id) REFERENCES city (id),
    CONSTRAINT fk_flight_route_to_city FOREIGN KEY (to_city_id) REFERENCES city (id)
);
