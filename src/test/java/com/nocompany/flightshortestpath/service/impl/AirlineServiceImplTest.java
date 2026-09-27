package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.model.Airline;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.repository.AirlineRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AirlineServiceImplTest {

    private final AirlineRepository airlineRepository = mock(AirlineRepository.class);
    private final AirlineServiceImpl service = new AirlineServiceImpl(airlineRepository);

    @Test
    void returnsAllRoutesForThePersistedAirline() {
        Airline airline = new Airline();
        airline.setId(1L);
        List<FlightRoute> routes = List.of(new FlightRoute(), new FlightRoute());
        airline.setFlightRoutes(routes);
        when(airlineRepository.findById(1L)).thenReturn(Optional.of(airline));

        List<FlightRoute> result = service.findAllFlightRouteByAirline(airline);

        assertSame(routes, result);
        verify(airlineRepository).findById(1L);
    }

    @Test
    void returnsEmptyListWhenAirlineDoesNotExist() {
        Airline airline = new Airline();
        airline.setId(99L);
        when(airlineRepository.findById(99L)).thenReturn(Optional.empty());

        List<FlightRoute> result = service.findAllFlightRouteByAirline(airline);

        assertEquals(List.of(), result);
        verify(airlineRepository).findById(99L);
    }
}
