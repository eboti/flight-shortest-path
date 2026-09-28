package com.nocompany.flightshortestpath;

import com.nocompany.flightshortestpath.repository.AirlineRepository;
import com.nocompany.flightshortestpath.repository.CityRepository;
import com.nocompany.flightshortestpath.repository.FlightRouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "app.sample-data.enabled=false")
class SampleDataDisabledIntegrationTest {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FlightRouteRepository flightRouteRepository;

    @Test
    void flywayCreatesSchemaWithoutInsertingSampleRows() {
        assertEquals(0, airlineRepository.count());
        assertEquals(0, cityRepository.count());
        assertEquals(0, flightRouteRepository.count());
    }
}
