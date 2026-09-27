package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
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

    private Integer distanceKm;
    private Integer durationMinutes;
}
