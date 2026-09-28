package com.nocompany.flightshortestpath.controller;

import com.nocompany.flightshortestpath.dto.DatabaseInitializationRequest;
import com.nocompany.flightshortestpath.dto.DatabaseInitializationResult;
import com.nocompany.flightshortestpath.service.DatabaseInitializationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/initial-data")
public class DatabaseInitializationController {

    private final DatabaseInitializationService initializationService;

    public DatabaseInitializationController(DatabaseInitializationService initializationService) {
        this.initializationService = initializationService;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public DatabaseInitializationResult replaceData(@RequestBody DatabaseInitializationRequest request) {
        return initializationService.replaceData(request);
    }
}
