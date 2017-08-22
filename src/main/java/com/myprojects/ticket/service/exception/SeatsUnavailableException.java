package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * This exception is thrown when no seats available in hall 
 */
public class SeatsUnavailableException extends TicketServiceException {

	private static final long serialVersionUID = -4771178387574016647L;
	
	public SeatsUnavailableException() {
		super(TicketServiceErrorType.NOT_ENOUGH_SEATS);
	}
	
	public SeatsUnavailableException(String message) {
		super(message, TicketServiceErrorType.NOT_ENOUGH_SEATS);
	}
	
	public SeatsUnavailableException(Throwable t) {
		super(TicketServiceErrorType.NOT_ENOUGH_SEATS, t);
	}
	
	public SeatsUnavailableException(String message, Throwable t) {
		super(message, TicketServiceErrorType.NOT_ENOUGH_SEATS, t);
	}
	
	public SeatsUnavailableException(TicketServiceErrorType errorType) {
		super(errorType);
	}
	
	public SeatsUnavailableException(String message, TicketServiceErrorType errorType) {
		super(message, errorType);
	}
	
	public SeatsUnavailableException(TicketServiceErrorType errorType, Throwable t) {
		super(errorType, t);
	}
	
	public SeatsUnavailableException(String message, TicketServiceErrorType errorType, Throwable t) {
		super(message, errorType, t);
	}
}
