package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    private Long id;
    private String name;
    private int population;
}
