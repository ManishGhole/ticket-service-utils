package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * This exception is thrown when desired SeatHold reservation not found
 */
public class InvalidSeatHoldException extends TicketServiceException {

	private static final long serialVersionUID = -4771178387574016647L;
	
	public InvalidSeatHoldException() {
		super(TicketServiceErrorType.SEAT_HOLD_ID_NOT_FOUND);
	}
	
	public InvalidSeatHoldException(String message) {
		super(message, TicketServiceErrorType.SEAT_HOLD_ID_NOT_FOUND);
	}
	
	public InvalidSeatHoldException(Throwable t) {
		super(TicketServiceErrorType.SEAT_HOLD_ID_NOT_FOUND, t);
	}
	
	public InvalidSeatHoldException(String message, Throwable t) {
		super(message, TicketServiceErrorType.SEAT_HOLD_ID_NOT_FOUND, t);
	}
	
	public InvalidSeatHoldException(TicketServiceErrorType errorType) {
		super(errorType);
	}

	public InvalidSeatHoldException(String message, TicketServiceErrorType errorType) {
		super(message, errorType);
	}

	public InvalidSeatHoldException(TicketServiceErrorType errorType, Throwable t) {
		super(errorType, t);
	}

	public InvalidSeatHoldException(String message, TicketServiceErrorType errorType, Throwable t) {
		super(message, errorType, t);
	}
}
