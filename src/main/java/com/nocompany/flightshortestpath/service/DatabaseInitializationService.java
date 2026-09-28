package com.nocompany.flightshortestpath.service;

import com.nocompany.flightshortestpath.dto.DatabaseInitializationRequest;
import com.nocompany.flightshortestpath.dto.DatabaseInitializationResult;
import com.nocompany.flightshortestpath.model.Airline;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.repository.AirlineRepository;
import com.nocompany.flightshortestpath.repository.CityRepository;
import com.nocompany.flightshortestpath.repository.FlightRouteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DatabaseInitializationService {

    private final AirlineRepository airlineRepository;
    private final CityRepository cityRepository;
    private final FlightRouteRepository flightRouteRepository;

    public DatabaseInitializationService(
            AirlineRepository airlineRepository,
            CityRepository cityRepository,
            FlightRouteRepository flightRouteRepository
    ) {
        this.airlineRepository = airlineRepository;
        this.cityRepository = cityRepository;
        this.flightRouteRepository = flightRouteRepository;
    }

    @Transactional
    public DatabaseInitializationResult replaceData(DatabaseInitializationRequest request) {
        validateRequest(request);

        Map<Long, Airline> airlinesById = request.airlines().stream()
                .map(data -> {
                    Airline airline = new Airline();
                    airline.setId(data.id());
                    airline.setName(data.name());
                    return airline;
                })
                .collect(Collectors.toMap(Airline::getId, Function.identity()));

        Map<Long, City> citiesById = request.cities().stream()
                .map(data -> {
                    City city = new City();
                    city.setId(data.id());
                    city.setName(data.name());
                    city.setPopulation(data.population());
                    return city;
                })
                .collect(Collectors.toMap(City::getId, Function.identity()));

        flightRouteRepository.deleteAllInBatch();
        airlineRepository.deleteAllInBatch();
        cityRepository.deleteAllInBatch();

        Map<Long, Airline> savedAirlines = airlineRepository.saveAll(airlinesById.values()).stream()
                .collect(Collectors.toMap(Airline::getId, Function.identity()));
        Map<Long, City> savedCities = cityRepository.saveAll(citiesById.values()).stream()
                .collect(Collectors.toMap(City::getId, Function.identity()));

        List<FlightRoute> routes = request.flightRoutes().stream()
                .map(data -> {
                    FlightRoute route = new FlightRoute();
                    route.setId(data.id());
                    route.setAirline(savedAirlines.get(data.airlineId()));
                    route.setFromCity(savedCities.get(data.fromCityId()));
                    route.setToCity(savedCities.get(data.toCityId()));
                    route.setDistanceKm(data.distanceKm());
                    route.setDurationMinutes(data.durationMinutes());
                    return route;
                })
                .toList();

        flightRouteRepository.saveAll(routes);

        return new DatabaseInitializationResult(
                airlinesById.size(),
                citiesById.size(),
                routes.size()
        );
    }

    private void validateRequest(DatabaseInitializationRequest request) {
        if (request == null || request.airlines() == null
                || request.cities() == null || request.flightRoutes() == null) {
            throw new IllegalArgumentException("airlines, cities, and flightRoutes are required");
        }

        Set<Long> airlineIds = new HashSet<>();
        for (DatabaseInitializationRequest.AirlineData airline : request.airlines()) {
            if (airline == null || airline.id() == null || airline.name() == null || airline.name().isBlank()) {
                throw new IllegalArgumentException("Each airline requires an id and a non-blank name");
            }
            if (!airlineIds.add(airline.id())) {
                throw new IllegalArgumentException("Airline ids must be unique");
            }
        }

        Set<Long> cityIds = new HashSet<>();
        for (DatabaseInitializationRequest.CityData city : request.cities()) {
            if (city == null || city.id() == null || city.name() == null || city.name().isBlank()
                    || city.population() == null || city.population() < 0) {
                throw new IllegalArgumentException("Each city requires an id, a non-blank name, and a non-negative population");
            }
            if (!cityIds.add(city.id())) {
                throw new IllegalArgumentException("City ids must be unique");
            }
        }

        Set<Long> routeIds = new HashSet<>();
        for (DatabaseInitializationRequest.FlightRouteData route : request.flightRoutes()) {
            if (route == null || route.id() == null || route.airlineId() == null
                    || route.fromCityId() == null || route.toCityId() == null
                    || route.distanceKm() == null || route.distanceKm() < 0
                    || route.durationMinutes() == null || route.durationMinutes() < 0) {
                throw new IllegalArgumentException("Each flight route requires ids and non-negative distance and duration");
            }
            if (!routeIds.add(route.id())) {
                throw new IllegalArgumentException("Flight route ids must be unique");
            }
            if (!airlineIds.contains(route.airlineId())
                    || !cityIds.contains(route.fromCityId()) || !cityIds.contains(route.toCityId())) {
                throw new IllegalArgumentException("Each flight route must reference supplied airlines and cities");
            }
        }
    }
}
