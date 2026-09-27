package com.nocompany.flightshortestpath.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
