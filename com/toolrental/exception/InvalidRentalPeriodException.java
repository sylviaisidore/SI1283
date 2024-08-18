package com.toolrental.exception;

@SuppressWarnings("serial")
public class InvalidRentalPeriodException extends RuntimeException {
    public InvalidRentalPeriodException(String message) {
        super(message);
    }
}
