package com.nocompany.flightshortestpath;

import com.nocompany.flightshortestpath.service.AirlineService;
import com.nocompany.flightshortestpath.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
@RequiredArgsConstructor
public class FlightShortestPathApplication implements CommandLineRunner {

    private final CityService cityService;
    private final AirlineService airlineService;

    @Override
    public void run(String... args) {
        log.info("Finding all airline company: ");
        airlineService.findAll().forEach(airline -> log.info(airline.getName()));
        if (!cityService.findAll().isEmpty()) {
            log.info("Smallest city: " + cityService.getSmallestCity());
            log.info("Biggest city: " + cityService.getBiggestCity());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightShortestPathApplication.class, args);
    }

}
