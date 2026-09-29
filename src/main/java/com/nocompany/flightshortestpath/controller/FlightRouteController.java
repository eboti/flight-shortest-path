package com.nocompany.flightshortestpath.controller;

import com.nocompany.flightshortestpath.dto.CityResponse;
import com.nocompany.flightshortestpath.dto.ExtremeCitiesResponse;
import com.nocompany.flightshortestpath.dto.FlightRouteResponse;
import com.nocompany.flightshortestpath.dto.RouteResult;
import com.nocompany.flightshortestpath.dto.ShortestRouteResponse;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.service.AirlineService;
import com.nocompany.flightshortestpath.service.CityService;
import com.nocompany.flightshortestpath.service.FlightRouteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightRouteController {

    private final FlightRouteService flightRouteService;
    private final CityService cityService;
    private final AirlineService airlineService;

    public FlightRouteController(
            FlightRouteService flightRouteService,
            CityService cityService,
            AirlineService airlineService
    ) {
        this.flightRouteService = flightRouteService;
        this.cityService = cityService;
        this.airlineService = airlineService;
    }

    @GetMapping("/flight-routes")
    public List<FlightRouteResponse> findFlightRoutes(
            @RequestParam(required = false) Long fromCityId,
            @RequestParam(required = false) Long toCityId
    ) {
        return flightRouteService.findAll().stream()
                .filter(route -> fromCityId == null || route.getFromCity().getId().equals(fromCityId))
                .filter(route -> toCityId == null || route.getToCity().getId().equals(toCityId))
                .map(FlightRouteResponse::from)
                .toList();
    }

    @GetMapping("/cities/extremes")
    public ExtremeCitiesResponse findPopulationExtremes() {
        return new ExtremeCitiesResponse(
                CityResponse.from(cityService.getSmallestCity()),
                CityResponse.from(cityService.getBiggestCity())
        );
    }

    @GetMapping("/routes/shortest")
    public ShortestRouteResponse findShortestRoute(
            @RequestParam(required = false) Long airlineId
    ) {
        City smallestCity = cityService.getSmallestCity();
        City largestCity = cityService.getBiggestCity();
        List<FlightRoute> routes = flightRouteService.findAll();

        if (airlineId != null) {
            if (airlineService.findAll().stream().noneMatch(airline -> airline.getId().equals(airlineId))) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given flight company is not found!");
            }
            routes = routes.stream()
                    .filter(route -> route.getAirline().getId().equals(airlineId))
                    .toList();
        }

        RouteResult result = flightRouteService.findShortestRoute(smallestCity, largestCity, routes);
        return ShortestRouteResponse.from(result);
    }
}
