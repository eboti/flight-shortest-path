package com.nocompany.flightshortestpath.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Getter
@Setter
@ToString
public class City {
    @Id
    private Long id;
    private String name;
    private int population;
}
