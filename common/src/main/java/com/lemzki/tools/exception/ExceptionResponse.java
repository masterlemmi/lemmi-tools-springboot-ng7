package com.lemzki.tools.exception;

import java.util.Date;
import java.util.Map;

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private Object details;

	public ExceptionResponse(Date timestamp, String message, Map<String, String> details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public ExceptionResponse(Date timestamp, String message, Object description) {
		this.timestamp = timestamp;
		this.message = message;
		this.details = description;

	}

	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public Object getDetails() {
		return details;
	}

}
