package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * This exception is thrown when request times out while confirming the reservation 
 */
public class ReservationTimeoutException extends TicketServiceException {

	private static final long serialVersionUID = -4771178387574016647L;
	
	public ReservationTimeoutException() {
		super(TicketServiceErrorType.SEAT_HOLD_RESERVATION_TIMEOUT);
	}
	
	public ReservationTimeoutException(String message) {
		super(message, TicketServiceErrorType.SEAT_HOLD_RESERVATION_TIMEOUT);
	}
	
	public ReservationTimeoutException(Throwable t) {
		super(TicketServiceErrorType.SEAT_HOLD_RESERVATION_TIMEOUT, t);
	}
	
	public ReservationTimeoutException(String message, Throwable t) {
		super(message, TicketServiceErrorType.SEAT_HOLD_RESERVATION_TIMEOUT, t);
	}
}
