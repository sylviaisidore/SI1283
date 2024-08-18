package com.toolrental.exception;

@SuppressWarnings("serial")
public class ToolRentalServiceException extends RuntimeException{

	public ToolRentalServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
