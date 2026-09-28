package com.nocompany.flightshortestpath.dto;

public record DatabaseInitializationResult(
        int airlines,
        int cities,
        int flightRoutes
) {
}
