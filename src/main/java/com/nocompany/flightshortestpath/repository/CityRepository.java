package com.nocompany.flightshortestpath.repository;

import com.nocompany.flightshortestpath.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
