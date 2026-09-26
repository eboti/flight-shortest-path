package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Airline {
    @Id
    private Long id;
    private String name;

    @OneToMany(mappedBy = "airline")
    private List<FlightRoute> flightRoutes;
}
