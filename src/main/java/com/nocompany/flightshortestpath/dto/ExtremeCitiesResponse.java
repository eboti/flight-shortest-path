package com.nocompany.flightshortestpath.dto;

public record ExtremeCitiesResponse(
        CityResponse smallestCity,
        CityResponse largestCity
) {
}
