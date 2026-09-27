package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.dto.RouteResult;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;

import java.util.List;
import java.util.function.Predicate;

public interface FlightRouteService {
    List<FlightRoute> findAll();

    RouteResult findShortestRoute(
            City from,
            City to,
            List<FlightRoute> flightRoutes
    );

    // TODO this impl or in AirlineService?
    void findAllRouteByAirline();
}
