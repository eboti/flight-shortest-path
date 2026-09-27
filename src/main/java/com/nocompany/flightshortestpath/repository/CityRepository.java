package com.nocompany.flightshortestpath.repository;

import com.nocompany.flightshortestpath.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {
    Optional<City> findFirstByOrderByPopulationAsc();

    Optional<City> findFirstByOrderByPopulationDesc();
}
