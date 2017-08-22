package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * Super exception for application ticket-service<br>
 * Mostly used for generic errors and non-severe exceptions
 */
public class TicketServiceException extends Exception {

	private static final long serialVersionUID = -4075988338215308781L;
	
	private TicketServiceErrorType errorType;
	
	public TicketServiceException(TicketServiceErrorType errorType) {
		super(errorType.toString());
		this.errorType = errorType;
	}
	
	public TicketServiceException(String message, TicketServiceErrorType errorType) {
		super(generateErrMsg(message, errorType));
		this.errorType = errorType;
	}
	
	public TicketServiceException(TicketServiceErrorType errorType, Throwable t) {
		super(errorType.toString(), t);
		this.errorType = errorType;
	}
	
	public TicketServiceException(String message, TicketServiceErrorType errorType, Throwable t) {
		super(generateErrMsg(message, errorType),t);
		this.errorType = errorType;
	}

	public TicketServiceErrorType getErrorType() {
		return errorType;
	}
	
	private static String generateErrMsg(String message, TicketServiceErrorType errorType) {
		return new StringBuilder(String.valueOf(errorType)).append(": ").append(message).toString();
	}
}
