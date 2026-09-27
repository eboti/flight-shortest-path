package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.exception.CityNotFoundException;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.repository.CityRepository;
import com.nocompany.flightshortestpath.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public City getSmallestCity() {
        return cityRepository.findFirstByOrderByPopulationAsc()
                .orElseThrow(() -> new CityNotFoundException("No cities found"));
    }

    public City getBiggestCity() {
        return cityRepository.findFirstByOrderByPopulationDesc()
                .orElseThrow(() -> new CityNotFoundException("No cities found"));
    }

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
