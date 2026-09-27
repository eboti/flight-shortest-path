package com.nocompany.flightshortestpath.dto;

import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;

import java.util.List;

public record RouteResult(
        City source,
        City target,
        List<FlightRoute> routes,
        int totalDistanceKm,
        int totalFlightMinutes,
        int totalWaitingMinutes
) {

    public int totalMinutes() {
        return totalFlightMinutes + totalWaitingMinutes;
    }

    public static RouteResult noPath(
            City source,
            City target) {

        return new RouteResult(
                source,
                target,
                List.of(),
                0,
                0,
                0
        );
    }
}