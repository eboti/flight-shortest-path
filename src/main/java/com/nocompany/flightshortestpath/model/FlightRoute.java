package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FlightRoute {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "airlineId")
    private Airline airline;

    @ManyToOne
    @JoinColumn(name = "fromCityId")
    private City fromCity;

    @ManyToOne
    @JoinColumn(name = "toCityId")
    private City toCity;

    private Double distanceKm;
    private Integer durationMinutes;
}
