package com.myprojects.ticket.service.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.myprojects.ticket.service.enums.SeatType;
import com.myprojects.ticket.service.exception.SeatHoldAlreadyConfirmedException;
import com.myprojects.ticket.service.utils.TicketServiceUtils;

public class SeatHoldTest {
	
	int maxRows = 4;
	int maxCols = 3;
	int maxGoldRows = 1;
	int maxSilverRows = 1;
	float seatRate = 40;
	Map<String, Seat> seatsMap = null;
	Map<String, SeatHold> seatHoldMap = null;
	String email = "test@email.com";
	
	private Map<String, Seat> generateSeatMap(int maxRows, int maxCols, int maxGoldRows, int maxSilverRows) {
		Map<String, Seat> availSeatsMap = new ConcurrentHashMap<String, Seat>();
		for(int rowId=1 ; rowId<=maxRows ; rowId++) {
			for(int colId=1 ; colId<=maxCols ; colId++) {
				SeatType seatType = TicketServiceUtils.identifySeatType(rowId, maxGoldRows, maxSilverRows);
				int rank = TicketServiceUtils.calculateSeatRank(rowId, colId, maxRows, maxCols);
				Seat seat = new Seat(rowId, colId, seatType, rank, seatRate);
				availSeatsMap.put(seat.getSeatId(), seat);
			}
		}
		return availSeatsMap;
	}
	
	@Before
	public void setup() {
		seatsMap = generateSeatMap(maxRows, maxCols, maxGoldRows, maxSilverRows);
		seatHoldMap = new ConcurrentHashMap<String, SeatHold>();
	}
	
	@Test
	public void testObjectMethods() {
		SeatHold seatHold1 = new SeatHold(email, null);
		SeatHold seatHold2 = new SeatHold(email, null);
		
		Assert.assertTrue(seatHold1.equals(seatHold1));
		Assert.assertTrue(seatHold1.hashCode() == seatHold1.hashCode());
		
		Assert.assertFalse(seatHold1.equals(seatHold2));
		Assert.assertFalse(seatHold2.equals(seatHold1));
		Assert.assertFalse(seatHold1.hashCode() == seatHold2.hashCode());
		
		Assert.assertFalse(seatHold1.equals(this));
		Assert.assertFalse(seatHold1.equals(null));
	}
	
	@Test
	public void testReserveAndConfirmationId() throws SeatHoldAlreadyConfirmedException {
		SeatHold seatHold = new SeatHold(email, null);
		Assert.assertFalse(seatHold.isReserved());
		seatHold.markAsReserved();
		String confirmationId = seatHold.getConfirmationId();
		Assert.assertTrue(seatHold.isReserved());
		String predictedConfirmId = TicketServiceUtils.getBase36(seatHold.getTimestamp().getTime()) + "-" + seatHold.getSeatHoldId();
		Assert.assertEquals(confirmationId, predictedConfirmId);
	}
	
	
	@Test
	public void testSeatHoldId() {
		SeatHold seatHold1 = new SeatHold(email, null);
		SeatHold seatHold2 = new SeatHold(email, null);
		Assert.assertFalse(seatHold1.getSeatHoldId() == seatHold2.getSeatHoldId());
	}
	
	@Test
	public void testSeatHoldPrice() {
		List<Seat> seats = null;
		Assert.assertEquals(0, new SeatHold(email, seats).getPrice(), 0);

		seats = new ArrayList<Seat>();
		Assert.assertEquals(0, new SeatHold(email, seats).getPrice(), 0);
		
		seats.add(new Seat(1, 1, SeatType.GOLD, 1, seatRate));
		seats.add(new Seat(1, 2, SeatType.GOLD, 2, seatRate));
		Assert.assertEquals(seatRate*2, new SeatHold(email, seats).getPrice(), 0);
		
	}
}
