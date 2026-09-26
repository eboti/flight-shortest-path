package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.model.Airline;
import com.nocompany.flightshortestpath.repository.AirlineRepository;
import com.nocompany.flightshortestpath.service.AirlineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    public AirlineServiceImpl(AirlineRepository airlineRepository) {
        this.airlineRepository = airlineRepository;
    }

    @Override
    public List<Airline> findAll() {
        return airlineRepository.findAll();
    }
}
