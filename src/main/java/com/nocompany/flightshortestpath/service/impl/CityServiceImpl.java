package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.repository.CityRepository;
import com.nocompany.flightshortestpath.service.CityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
