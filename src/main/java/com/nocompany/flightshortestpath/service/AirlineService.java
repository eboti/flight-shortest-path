package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.model.Airline;
import com.nocompany.flightshortestpath.model.FlightRoute;

import java.util.List;

public interface AirlineService {
    List<Airline> findAll();
    List<FlightRoute> findAllFlightRouteByAirline(Airline airline);
}

