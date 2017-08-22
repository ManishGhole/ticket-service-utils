package com.myprojects.ticket.service.beans.comparators;

import java.util.Comparator;

import com.myprojects.ticket.service.beans.Seat;

public class SeatRankComparator implements Comparator<Seat> {

	public int compare(Seat seat1, Seat seat2) {
		return seat1.compareTo(seat2);
	}

}
