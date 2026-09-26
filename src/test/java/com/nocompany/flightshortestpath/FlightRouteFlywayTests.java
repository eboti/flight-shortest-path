package com.nocompany.flightshortestpath;

import com.nocompany.flightshortestpath.model.Airline;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.service.AirlineService;
import com.nocompany.flightshortestpath.service.CityService;
import com.nocompany.flightshortestpath.service.FlightRouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FlightRouteFlywayTests {

    @Autowired
    private AirlineService airlineService;

    @Autowired
    private CityService cityService;

    @Autowired
    private FlightRouteService flightRouteService;

    @Test
    @Transactional
    void servicesReturnExampleDataFromRepositories() {
        Airline airline = airlineService.findAll().stream()
                .filter(candidate -> candidate.getId().equals(1L))
                .findFirst()
                .orElseThrow();
        City city = cityService.findAll().stream()
                .filter(candidate -> candidate.getId().equals(1L))
                .findFirst()
                .orElseThrow();
        FlightRoute route = flightRouteService.findAll().stream()
                .filter(candidate -> candidate.getId().equals(1001L))
                .findFirst()
                .orElseThrow();

        assertEquals(2, airlineService.findAll().size());
        assertEquals(5, cityService.findAll().size());
        assertEquals(6, flightRouteService.findAll().size());
        assertEquals("Northstar Air", airline.getName());
        assertEquals("Boston", city.getName());
        assertEquals("Northstar Air", route.getAirline().getName());
        assertEquals("Boston", route.getFromCity().getName());
        assertEquals("New York", route.getToCity().getName());
    }
}
