package com.nocompany.flightshortestpath.controller;

import com.nocompany.flightshortestpath.repository.AirlineRepository;
import com.nocompany.flightshortestpath.repository.CityRepository;
import com.nocompany.flightshortestpath.repository.FlightRouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DatabaseInitializationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private FlightRouteRepository flightRouteRepository;

    @Test
    void replacesDatabaseContentWithJsonAndRejectsInvalidReferencesWithoutChangingIt() throws Exception {
        String validRequest = new ClassPathResource("sample-data.json")
                .getContentAsString(StandardCharsets.UTF_8);

        mockMvc.perform(put("/api/initial-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(validRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.airlines").value(2))
                .andExpect(jsonPath("$.cities").value(3))
                .andExpect(jsonPath("$.flightRoutes").value(2));

        assertEquals(2, airlineRepository.count());
        assertEquals(3, cityRepository.count());
        assertEquals(2, flightRouteRepository.count());
        assertFalse(cityRepository.existsById(4L));
        var route = flightRouteRepository.findById(1001L).orElseThrow();
        assertEquals("Wizz Air", route.getAirline().getName());
        assertEquals("Budapest", route.getFromCity().getName());
        assertEquals("Debrecen", route.getToCity().getName());

        String invalidRequest = """
                {
                  "airlines": [{"id": 60, "name": "Invalid Air"}],
                  "cities": [{"id": 60, "name": "Only City", "population": 1}],
                  "flightRoutes": [
                    {
                      "id": 600,
                      "airlineId": 60,
                      "fromCityId": 60,
                      "toCityId": 999,
                      "distanceKm": 10,
                      "durationMinutes": 10
                    }
                  ]
                }
                """;

        mockMvc.perform(put("/api/initial-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidRequest))
                .andExpect(status().isBadRequest());

        assertEquals(2, airlineRepository.count());
        assertEquals(3, cityRepository.count());
        assertEquals(2, flightRouteRepository.count());
        assertTrue(airlineRepository.existsById(1L));
    }
}
