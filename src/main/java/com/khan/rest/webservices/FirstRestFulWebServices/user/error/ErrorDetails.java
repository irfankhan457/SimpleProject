package com.khan.rest.webservices.FirstRestFulWebServices.user.error;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ErrorDetails {
	private LocalDateTime timestamp;
	private String message;
	private String details;
	private String errorGenerator;
	
	public ErrorDetails(LocalDateTime timestamp, String message, String details, String errorGenerator) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.errorGenerator = errorGenerator;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public String getMessage() {
		return message;
	}
	public String getDetails() {
		return details;
	}
	public String getErrorGenerator() {
		return errorGenerator;
	}
}
