package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.model.Airline;

import java.util.List;

public interface AirlineService {
    List<Airline> findAll();
}
