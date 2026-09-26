package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;

import java.util.function.Predicate;

public interface FlightRouteService {
    void findShortestRoute(
            City from,
            City to,
            Predicate<FlightRoute> flightFilter
    );

    void findAllRouteByAirline();
}
