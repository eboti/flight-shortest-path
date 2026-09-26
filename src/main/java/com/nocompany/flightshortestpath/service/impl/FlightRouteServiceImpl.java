package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.repository.FlightRouteRepository;
import com.nocompany.flightshortestpath.service.FlightRouteService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Log
@Service
public class FlightRouteServiceImpl implements FlightRouteService {

    private final FlightRouteRepository flightRouteRepository;

    public FlightRouteServiceImpl(FlightRouteRepository flightRouteRepository) {
        this.flightRouteRepository = flightRouteRepository;
    }

    @Override
    public List<FlightRoute> findAll() {
        return flightRouteRepository.findAll();
    }

    @Override
    public void findShortestRoute(City from, City to, Predicate<FlightRoute> flightFilter) {
        log.info("Finding shortest flight route...");
    }

    @Override
    public void findAllRouteByAirline() {
        log.info("Finding all flight routes by airline...");
    }
}
