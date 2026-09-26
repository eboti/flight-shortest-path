package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;

@Entity
public class City {
    private Long id;
    private String name;
    private int population;
}
