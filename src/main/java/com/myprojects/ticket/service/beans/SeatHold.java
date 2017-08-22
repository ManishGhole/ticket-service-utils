package com.myprojects.ticket.service.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang3.StringUtils;

import com.myprojects.ticket.service.utils.TicketServiceUtils;

/**
 * This class represents individual reservations
 */
public class SeatHold implements Serializable {
	private static final long serialVersionUID = 130628540108839507L;

	private static AtomicInteger seatHoldIdGen = new AtomicInteger(0);

	private Integer seatHoldId;
	private String seatHoldIdStr;
	private String confirmationId;
	private List<Seat> seats;
	private Date timestamp;
	private String customerEmail;
	private boolean expired;
	private boolean reserved;
	private float price;

	public SeatHold(String customerEmail, List<Seat> seats) {
		this.seatHoldId = seatHoldIdGen.incrementAndGet();
		this.seatHoldIdStr = String.valueOf(this.seatHoldId);
		this.customerEmail = customerEmail;
		this.seats = seats;
		this.price = calculatePrice(this.seats);
		this.timestamp = new Date();
	}

	/**
	 * Calculates the total price of reservation
	 * @param seats List of seats
	 * @return
	 */
	private float calculatePrice(List<Seat> seats) {
		float price = 0;
		if (seats != null) {
			for (Seat seat : seats) {
				if (seat != null) {
					price += seat.getRate();
				}
			}
		}
		return price;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SeatHold)) {
			return false;
		}
		return StringUtils.equals(this.seatHoldIdStr, obj.toString());
	}

	@Override
	public String toString() {
		return this.seatHoldIdStr;
	}

	@Override
	public int hashCode() {
		return this.seatHoldIdStr.hashCode();
	}

	public Integer getSeatHoldId() {
		return seatHoldId;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public boolean isReserved() {
		return reserved;
	}

	public boolean isExpired() {
		return expired;
	}

	public void markAsExpired() {
		this.expired = true;
	}

	public String getConfirmationId() {
		return this.confirmationId;
	}

	/**
	 * This method marks particular reservation confirmed and generates
	 * confirmationId
	 */
	public void markAsReserved() {
		if (this.confirmationId == null) {
			this.reserved = true;
			this.confirmationId = new StringBuilder(TicketServiceUtils.getBase36(this.timestamp.getTime())).append("-")
					.append(seatHoldId).toString();
		}
	}

	public float getPrice() {
		return this.price;
	}

}
