package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.dto.RouteResult;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;

import java.util.List;

public interface FlightRouteService {

    RouteResult findShortestRoute(
            City from,
            City to,
            List<FlightRoute> flightRoutes
    );

    List<FlightRoute> findAll();
}
