package com.toolrental.exception;

@SuppressWarnings("serial")
public class ToolNotFoundException extends RuntimeException {
    public ToolNotFoundException(String message) {
        super(message);
    }
}


