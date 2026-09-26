package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FlightRoute {
    @Id
    private Long id;
    private Long airline_id;
    private Long from_city_id;
    private Long to_city_id;
    private Double distance_km;
    private Integer duration_minutes;
}
