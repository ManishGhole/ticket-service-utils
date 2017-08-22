package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * This exception is thrown when requested for SeatHold is expired 
 */
public class SeatHoldExpiredException extends TicketServiceException {

	private static final long serialVersionUID = -4771178387574016647L;
	
	public SeatHoldExpiredException() {
		super(TicketServiceErrorType.SEAT_HOLD_EXPIRED);
	}
	
	public SeatHoldExpiredException(String message) {
		super(message, TicketServiceErrorType.SEAT_HOLD_EXPIRED);
	}
	
	public SeatHoldExpiredException(Throwable t) {
		super(TicketServiceErrorType.SEAT_HOLD_EXPIRED, t);
	}
	
	public SeatHoldExpiredException(String message, Throwable t) {
		super(message, TicketServiceErrorType.SEAT_HOLD_EXPIRED, t);
	}
}
