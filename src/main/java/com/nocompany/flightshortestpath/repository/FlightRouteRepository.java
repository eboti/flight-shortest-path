package com.nocompany.flightshortestpath.repository;

import com.nocompany.flightshortestpath.model.FlightRoute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRouteRepository extends JpaRepository<FlightRoute, Long> {
}
