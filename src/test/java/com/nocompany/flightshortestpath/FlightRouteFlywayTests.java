package com.nocompany.flightshortestpath;

import com.nocompany.flightshortestpath.model.FlightRoute;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class FlightRouteFlywayTests {

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void loadsExampleFlightData() {
        FlightRoute route = entityManager.find(FlightRoute.class, 1001L);

        assertEquals(6L, entityManager.createQuery(
                "select count(route) from FlightRoute route", Long.class).getSingleResult());
        assertEquals("Northstar Air", route.getAirline().getName());
        assertEquals("Boston", route.getFromCity().getName());
        assertEquals("New York", route.getToCity().getName());
    }
}
