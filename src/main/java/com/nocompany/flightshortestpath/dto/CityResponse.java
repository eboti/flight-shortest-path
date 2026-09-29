package com.nocompany.flightshortestpath.dto;

import com.nocompany.flightshortestpath.model.City;

public record CityResponse(Long id, String name, int population) {

    public static CityResponse from(City city) {
        return new CityResponse(city.getId(), city.getName(), city.getPopulation());
    }
}
