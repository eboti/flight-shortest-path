package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.service.FlightRouteService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class FlightRouteServiceImpl implements FlightRouteService {

    @Override
    public void findShortestPath() {
        log.info("Finding shortest flight path...");
    }
}
