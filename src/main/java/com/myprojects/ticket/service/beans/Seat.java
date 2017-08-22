package com.myprojects.ticket.service.beans;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.myprojects.ticket.service.enums.SeatType;

/**
 * This class represents individual seat in the theater hall
 */
public class Seat implements Serializable, Comparable<Seat> {

	private static final long serialVersionUID = -4168700476123719996L;
	private String seatId;
	private int rowId;
	private int colId;
	private SeatType seatType;
	private int rank;
	private float rate;

	public Seat(final int rowId, final int colId, final SeatType seatType, final int rank, final float rate) {
		this.rowId = rowId;
		this.colId = colId;
		this.seatType = seatType;
		this.seatId = new StringBuilder("R").append(rowId).append("-").append("C").append(colId).toString();
		this.rank = rank;
		this.rate = rate;
	}

	/**
	 * This method will used for sorting the seats based on rank
	 */
	public int compareTo(Seat seat) {
		if (this.rank < seat.getRank()) {
			return -1;
		}
		if (this.rank > seat.getRank()) {
			return 1;
		}
		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Seat)) {
			return false;
		}
		return StringUtils.equals(this.seatId, obj.toString());
	}

	@Override
	public String toString() {
		return this.seatId;
	}

	@Override
	public int hashCode() {
		return this.seatId.hashCode();
	}

	public String getSeatId() {
		return seatId;
	}

	public int getRowId() {
		return rowId;
	}

	public int getColId() {
		return colId;
	}

	public SeatType getSeatType() {
		return seatType;
	}

	protected int getRank() {
		return this.rank;
	}

	public float getRate() {
		return rate;
	}

}
