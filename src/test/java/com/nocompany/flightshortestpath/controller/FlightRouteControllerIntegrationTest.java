package com.nocompany.flightshortestpath.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FlightRouteControllerIntegrationTest {

    private static final String ROUTE_DATA = """
            {
              "airlines": [
                {"id": 10, "name": "Fast Connect"},
                {"id": 20, "name": "Remote Air"},
                {"id": 30, "name": "Direct Air"}
              ],
              "cities": [
                {"id": 1, "name": "Small City", "population": 100},
                {"id": 2, "name": "Transfer City", "population": 1000},
                {"id": 3, "name": "Large City", "population": 3000},
                {"id": 4, "name": "Remote City", "population": 500}
              ],
              "flightRoutes": [
                {
                  "id": 101,
                  "airlineId": 10,
                  "fromCityId": 1,
                  "toCityId": 2,
                  "distanceKm": 100,
                  "durationMinutes": 45
                },
                {
                  "id": 102,
                  "airlineId": 10,
                  "fromCityId": 2,
                  "toCityId": 3,
                  "distanceKm": 80,
                  "durationMinutes": 30
                },
                {
                  "id": 103,
                  "airlineId": 20,
                  "fromCityId": 4,
                  "toCityId": 3,
                  "distanceKm": 20,
                  "durationMinutes": 10
                },
                {
                  "id": 104,
                  "airlineId": 30,
                  "fromCityId": 1,
                  "toCityId": 3,
                  "distanceKm": 250,
                  "durationMinutes": 240
                }
              ]
            }
            """;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void exposesRouteListsCityExtremesAndShortestRouteVariants() throws Exception {
        mockMvc.perform(put("/api/initial-data")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(ROUTE_DATA))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/flight-routes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0].airlineName").exists());

        mockMvc.perform(get("/api/flight-routes")
                        .param("fromCityId", "1")
                        .param("toCityId", "3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").value(104));

        mockMvc.perform(get("/api/flight-routes").param("fromCityId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        mockMvc.perform(get("/api/cities/extremes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.smallestCity.name").value("Small City"))
                .andExpect(jsonPath("$.smallestCity.population").value(100))
                .andExpect(jsonPath("$.largestCity.name").value("Large City"))
                .andExpect(jsonPath("$.largestCity.population").value(3000));

        mockMvc.perform(get("/api/routes/shortest").param("airlineId", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reachable").value(true))
                .andExpect(jsonPath("$.totalDistanceKm").value(180))
                .andExpect(jsonPath("$.transfers.length()").value(1))
                .andExpect(jsonPath("$.transfers[0].name").value("Transfer City"))
                .andExpect(jsonPath("$.totalFlightMinutes").value(75))
                .andExpect(jsonPath("$.totalWaitingMinutes").value(15))
                .andExpect(jsonPath("$.totalMinutes").value(90));

        mockMvc.perform(get("/api/routes/shortest").param("airlineId", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reachable").value(false))
                .andExpect(jsonPath("$.message").value(
                        "No route found with the available flight companies!"));

        mockMvc.perform(get("/api/routes/shortest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.reachable").value(true))
                .andExpect(jsonPath("$.totalDistanceKm").value(180))
                .andExpect(jsonPath("$.totalMinutes").value(90));
    }
}
