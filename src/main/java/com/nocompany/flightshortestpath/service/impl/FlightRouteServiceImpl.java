package com.nocompany.flightshortestpath.service.impl;

import com.nocompany.flightshortestpath.dto.RouteResult;
import com.nocompany.flightshortestpath.model.City;
import com.nocompany.flightshortestpath.model.FlightRoute;
import com.nocompany.flightshortestpath.repository.FlightRouteRepository;
import com.nocompany.flightshortestpath.service.FlightRouteService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Log
@Service
public class FlightRouteServiceImpl implements FlightRouteService {

    private final FlightRouteRepository flightRouteRepository;

    public FlightRouteServiceImpl(FlightRouteRepository flightRouteRepository) {
        this.flightRouteRepository = flightRouteRepository;
    }

    @Override
    public List<FlightRoute> findAll() {
        return flightRouteRepository.findAll();
    }

    @Override
    public RouteResult findShortestRoute(
            City fromCity,
            City toCity,
            List<FlightRoute> routes) {

        log.info("Finding shortest flight route...");

        // Build adjacency list
        Map<City, List<FlightRoute>> graph = routes.stream()
                .collect(Collectors.groupingBy(FlightRoute::getFromCity));

        // Shortest known distance from source to each city
        Map<City, Integer> distances = new HashMap<>();

        // Previous route used to reach each city
        Map<City, FlightRoute> previousRoute = new HashMap<>();

        // Cities that still need to be processed
        PriorityQueue<CityDistance> queue =
                new PriorityQueue<>(Comparator.comparingInt(CityDistance::distance));

        distances.put(toCity, 0);
        queue.add(new CityDistance(fromCity, 0));

        while (!queue.isEmpty()) {

            CityDistance current = queue.poll();

            City currentCity = current.city();
            int currentDistance = current.distance();

            // Ignore outdated queue entries
            if (currentDistance > distances.getOrDefault(currentCity, Integer.MAX_VALUE)) {
                continue;
            }

            // Target reached
            if (currentCity.equals(toCity)) {
                break;
            }

            for (FlightRoute route : graph.getOrDefault(currentCity, List.of())) {
                City nextCity = route.getToCity();
                int newDistance = currentDistance + route.getDistanceKm();
                int knownDistance = distances.getOrDefault(nextCity, Integer.MAX_VALUE);

                if (newDistance < knownDistance) {
                    distances.put(nextCity, newDistance);
                    previousRoute.put(nextCity, route);
                    queue.add(new CityDistance(nextCity, newDistance));
                }
            }
        }

        // No path exists
        if (!distances.containsKey(toCity)) {
            return RouteResult.noPath(fromCity, toCity);
        }

        // Reconstruct path
        List<FlightRoute> shortestPath = new ArrayList<>();

        City current = toCity;

        while (!current.equals(fromCity)) {
            FlightRoute route = previousRoute.get(current);
            shortestPath.add(route);
            current = route.getFromCity();
        }

        Collections.reverse(shortestPath);

        return createRouteResult(fromCity, toCity, shortestPath);
    }

    @Override
    public void findAllRouteByAirline() {
        log.info("Finding all flight routes by airline...");
    }

    private record CityDistance(
            City city,
            int distance
    ) {}

    private RouteResult createRouteResult(
            City source,
            City target,
            List<FlightRoute> routes) {

        int totalDistanceKm = routes.stream()
                .mapToInt(FlightRoute::getDistanceKm)
                .sum();

        int totalFlightMinutes = routes.stream()
                .mapToInt(FlightRoute::getDurationMinutes)
                .sum();

        int totalWaitingMinutes = calculateWaitingTime(routes);

        return new RouteResult(
                source,
                target,
                routes,
                totalDistanceKm,
                totalFlightMinutes,
                totalWaitingMinutes
        );
    }

    private int calculateWaitingTime(List<FlightRoute> routes) {

        int currentTimeMinutes = 0;
        int waitingMinutes = 0;

        for (int i = 0; i < routes.size(); i++) {
            FlightRoute route = routes.get(i);

            // Flight time
            currentTimeMinutes += route.getDurationMinutes();

            // No waiting after the final flight
            if (i < routes.size() - 1) {
                int minutesPastFullHour = currentTimeMinutes % 60;
                int wait = (60 - minutesPastFullHour) % 60;

                waitingMinutes += wait;
                currentTimeMinutes += wait;
            }
        }

        return waitingMinutes;
    }
}
