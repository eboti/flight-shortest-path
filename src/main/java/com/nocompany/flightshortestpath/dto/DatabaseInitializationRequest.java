package com.nocompany.flightshortestpath.dto;

import java.util.List;

public record DatabaseInitializationRequest(
        List<AirlineData> airlines,
        List<CityData> cities,
        List<FlightRouteData> flightRoutes
) {
    public record AirlineData(Long id, String name) {
    }

    public record CityData(Long id, String name, Integer population) {
    }

    public record FlightRouteData(
            Long id,
            Long airlineId,
            Long fromCityId,
            Long toCityId,
            Integer distanceKm,
            Integer durationMinutes
    ) {
    }
}
