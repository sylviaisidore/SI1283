package com.toolrental.exception;

@SuppressWarnings("serial")
public class InvalidDiscountException extends RuntimeException {
    public InvalidDiscountException(String message) {
        super(message);
    }
}
