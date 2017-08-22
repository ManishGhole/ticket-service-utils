package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * This exception is thrown when request times out while requesting to hold reservation 
 */
public class SeatHoldTimeoutException extends TicketServiceException {

	private static final long serialVersionUID = -4771178387574016647L;
	
	public SeatHoldTimeoutException() {
		super(TicketServiceErrorType.SEAT_HOLD_TIMEOUT);
	}
	
	public SeatHoldTimeoutException(String message) {
		super(message, TicketServiceErrorType.SEAT_HOLD_TIMEOUT);
	}
	
	public SeatHoldTimeoutException(Throwable t) {
		super(TicketServiceErrorType.SEAT_HOLD_TIMEOUT, t);
	}
	
	public SeatHoldTimeoutException(String message, Throwable t) {
		super(message, TicketServiceErrorType.SEAT_HOLD_TIMEOUT, t);
	}
}
