package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;

import java.util.List;
import java.util.function.Predicate;

public interface FlightRouteService {
    List<FlightRoute> findAll();

    void findShortestRoute(
            City from,
            City to,
            Predicate<FlightRoute> flightFilter
    );

    void findAllRouteByAirline();
}
