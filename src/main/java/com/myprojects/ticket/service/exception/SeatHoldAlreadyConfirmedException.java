package com.myprojects.ticket.service.exception;

import com.myprojects.ticket.service.enums.TicketServiceErrorType;

/**
 * This exception is thrown when requested SeatHold reservation is already confirmed 
 */
public class SeatHoldAlreadyConfirmedException extends TicketServiceException {

	private static final long serialVersionUID = -4771178387574016647L;
	
	private String confirmationId;
	
	public SeatHoldAlreadyConfirmedException(String message, String confirmationId) {
		super(message, TicketServiceErrorType.SEAT_HOLD_ALREADY_CONFIRMED);
		this.confirmationId = confirmationId;
	}
	
	public SeatHoldAlreadyConfirmedException(Throwable t, String confirmationId) {
		super(TicketServiceErrorType.SEAT_HOLD_ALREADY_CONFIRMED, t);
		this.confirmationId = confirmationId;
	}
	
	public SeatHoldAlreadyConfirmedException(String message, Throwable t, String confirmationId) {
		super(message, TicketServiceErrorType.SEAT_HOLD_ALREADY_CONFIRMED, t);
		this.confirmationId = confirmationId;
	}

	public String getConfirmationId() {
		return confirmationId;
	}
}
