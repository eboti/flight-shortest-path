package com.nocompany.flightshortestpath.repository;

import com.nocompany.flightshortestpath.model.FlightRoute;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRouteRepository extends JpaRepository<FlightRoute, Long> {
    @Override
    @EntityGraph(attributePaths = {"airline", "fromCity", "toCity"})
    List<FlightRoute> findAll();
}
