package com.teamcubation.AccionService.domain.exception;

public class StockNotFoundException extends Exception {
    public StockNotFoundException(String message) {
        super(message);
    }
}
