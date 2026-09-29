package com.nocompany.flightshortestpath.dto;

import java.util.List;

public record ShortestRouteResponse(
        boolean reachable,
        String message,
        CityResponse source,
        CityResponse target,
        List<FlightRouteResponse> legs,
        List<CityResponse> transfers,
        int totalDistanceKm,
        int totalFlightMinutes,
        int totalWaitingMinutes,
        int totalMinutes
) {

    public static ShortestRouteResponse from(RouteResult result) {
        List<FlightRouteResponse> legs = result.routes().stream()
                .map(FlightRouteResponse::from)
                .toList();
        List<CityResponse> transfers = result.routes().stream()
                .limit(Math.max(0, result.routes().size() - 1))
                .map(route -> CityResponse.from(route.getToCity()))
                .toList();

        boolean reachable = !result.routes().isEmpty() || result.source().getId().equals(result.target().getId());
        return new ShortestRouteResponse(
                reachable,
                reachable ? null : "No route found with the available flight companies!",
                CityResponse.from(result.source()),
                CityResponse.from(result.target()),
                legs,
                transfers,
                result.totalDistanceKm(),
                result.totalFlightMinutes(),
                result.totalWaitingMinutes(),
                result.totalMinutes()
        );
    }
}
