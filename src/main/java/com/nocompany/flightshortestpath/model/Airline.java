package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Airline {
    @Id
    private Long id;
    private String name;
}
