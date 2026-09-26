package com.nocompany.flightshortestpath.repository;

import com.nocompany.flightshortestpath.model.Airline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirlineRepository extends JpaRepository<Airline, Long> {
}
