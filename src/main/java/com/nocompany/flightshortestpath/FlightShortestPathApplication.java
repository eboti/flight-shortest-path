package com.nocompany.flightshortestpath;

import com.nocompany.flightshortestpath.service.FlightRouteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FlightShortestPathApplication implements CommandLineRunner {

    private final FlightRouteService flightRouteService;

    public FlightShortestPathApplication(FlightRouteService flightRouteService) {
        this.flightRouteService = flightRouteService;
    }

    @Override
    public void run(String... args) {
        flightRouteService.findShortestRoute(null, null, null);
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightShortestPathApplication.class, args);
    }

}
