package com.nocompany.flightshortestpath.dto;

import com.nocompany.flightshortestpath.model.FlightRoute;

public record FlightRouteResponse(
        Long id,
        Long airlineId,
        String airlineName,
        Long fromCityId,
        String fromCity,
        Long toCityId,
        String toCity,
        Integer distanceKm,
        Integer durationMinutes
) {

    public static FlightRouteResponse from(FlightRoute route) {
        return new FlightRouteResponse(
                route.getId(),
                route.getAirline().getId(),
                route.getAirline().getName(),
                route.getFromCity().getId(),
                route.getFromCity().getName(),
                route.getToCity().getId(),
                route.getToCity().getName(),
                route.getDistanceKm(),
                route.getDurationMinutes()
        );
    }
}
