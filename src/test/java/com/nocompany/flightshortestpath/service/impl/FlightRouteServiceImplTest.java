package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.dto.RouteResult;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.repository.FlightRouteRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class FlightRouteServiceImplTest {

    private final FlightRouteServiceImpl service =
            new FlightRouteServiceImpl(mock(FlightRouteRepository.class));

    @Test
    void returnsShortestDistanceRouteAndTotals() {
        City from = city(1L, "Origin");
        City connectionA = city(2L, "Connection A");
        City connectionB = city(3L, "Connection B");
        City to = city(4L, "Destination");

        FlightRoute toConnectionA = route(from, connectionA, 200, 70);
        FlightRoute toConnectionB = route(from, connectionB, 100, 30);
        FlightRoute connectionBToA = route(connectionB, connectionA, 50, 45);
        FlightRoute connectionAToDestination = route(connectionA, to, 100, 35);
        FlightRoute directFlight = route(from, to, 400, 240);

        RouteResult result = service.findShortestRoute(
                from,
                to,
                List.of(toConnectionA, toConnectionB, connectionBToA,
                        connectionAToDestination, directFlight));

        assertSame(from, result.source());
        assertSame(to, result.target());
        assertEquals(List.of(toConnectionB, connectionBToA, connectionAToDestination), result.routes());
        assertEquals(250, result.totalDistanceKm());
        assertEquals(110, result.totalFlightMinutes());
        assertEquals(45, result.totalWaitingMinutes());
        assertEquals(155, result.totalMinutes());
    }

    @Test
    void returnsNoPathWhenDestinationIsUnreachable() {
        City from = city(1L, "Origin");
        City disconnected = city(2L, "Disconnected");
        City to = city(3L, "Destination");

        RouteResult result = service.findShortestRoute(
                from,
                to,
                List.of(route(from, disconnected, 100, 60)));

        assertSame(from, result.source());
        assertSame(to, result.target());
        assertEquals(List.of(), result.routes());
        assertEquals(0, result.totalDistanceKm());
        assertEquals(0, result.totalFlightMinutes());
        assertEquals(0, result.totalWaitingMinutes());
    }

    @Test
    void returnsEmptyRouteWhenSourceAndDestinationAreTheSame() {
        City city = city(1L, "Same city");

        RouteResult result = service.findShortestRoute(
                city,
                city,
                List.of(route(city, city(2L, "Other city"), 100, 60)));

        assertSame(city, result.source());
        assertSame(city, result.target());
        assertEquals(List.of(), result.routes());
        assertEquals(0, result.totalDistanceKm());
        assertEquals(0, result.totalFlightMinutes());
        assertEquals(0, result.totalWaitingMinutes());
    }

    private static City city(Long id, String name) {
        City city = new City();
        city.setId(id);
        city.setName(name);
        return city;
    }

    private static FlightRoute route(City from, City to, int distanceKm, int durationMinutes) {
        FlightRoute route = new FlightRoute();
        route.setFromCity(from);
        route.setToCity(to);
        route.setDistanceKm(distanceKm);
        route.setDurationMinutes(durationMinutes);
        return route;
    }
}
