package com.myprojects.ticket.service.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.myprojects.ticket.service.enums.SeatType;
import com.myprojects.ticket.service.utils.TicketServiceUtils;

public class SeatTest {
	
	int maxRows = 4;
	int maxCols = 3;
	int maxGoldRows = 1;
	int maxSilverRows = 1;
	float seatRate = 40;
	Map<String, Seat> seatsMap = null;
	
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
	}
	
	@Test
	public void testSeatId() {
		Assert.assertEquals(new Seat(1, 1, null, 1, seatRate).toString(), "R1-C1");
	}
	
	@Test
	public void testObjectMethods() {
		int rowId = 1;
		int colId = 2;
		SeatType seatType = TicketServiceUtils.identifySeatType(rowId, maxGoldRows, maxSilverRows);
		int rank = TicketServiceUtils.calculateSeatRank(rowId, colId, maxRows, maxCols);
		Seat seat1 = new Seat(rowId, colId, seatType, rank, seatRate);
		Seat seat2 = new Seat(rowId, colId, seatType, rank, seatRate);
		Assert.assertTrue(seat1.equals(seat1));
		Assert.assertTrue(seat1.equals(seat2));
		Assert.assertTrue(seat2.equals(seat1));
		Assert.assertTrue(seat1.hashCode() == seat2.hashCode());
		
		colId = 3;
		seatType = TicketServiceUtils.identifySeatType(rowId, maxGoldRows, maxSilverRows);
		rank = TicketServiceUtils.calculateSeatRank(rowId, colId, maxRows, maxCols);
		Seat seat3 = new Seat(rowId, colId, seatType, rank, seatRate);
		Assert.assertFalse(seat1.equals(seat3));
		Assert.assertFalse(seat3.equals(seat1));
		Assert.assertFalse(seat1.hashCode() == seat3.hashCode());
		
		Assert.assertFalse(seat1.equals(this));
		Assert.assertFalse(seat1.equals(null));
	}
	
	@Test
	public void testSeatType() {
		Assert.assertEquals(seatsMap.get("R1-C1").getSeatType(), SeatType.GOLD);
		Assert.assertEquals(seatsMap.get("R1-C3").getSeatType(), SeatType.GOLD);
		Assert.assertEquals(seatsMap.get("R2-C1").getSeatType(), SeatType.SILVER);
		Assert.assertEquals(seatsMap.get("R2-C3").getSeatType(), SeatType.SILVER);
		Assert.assertEquals(seatsMap.get("R3-C1").getSeatType(), SeatType.BRONZE);
		Assert.assertEquals(seatsMap.get("R4-C3").getSeatType(), SeatType.BRONZE);
	}
	
	@Test
	public void testSeatRank() {
		Assert.assertTrue(seatsMap.get("R1-C1").getRank() < seatsMap.get("R1-C2").getRank());
		Assert.assertTrue(seatsMap.get("R1-C2").getRank() < seatsMap.get("R1-C3").getRank());
		Assert.assertTrue(seatsMap.get("R2-C3").getRank() < seatsMap.get("R2-C1").getRank());
	}
	
	@Test
	public void testSeatOrderByRank() {
		List<Seat> seats = new ArrayList<Seat>(seatsMap.values());
		Collections.sort(seats);
		Assert.assertTrue(seats.get(0).getRank() < seats.get(1).getRank());
		Assert.assertTrue(seats.get(3).getRank() > seats.get(2).getRank());
		Set<Seat> set = new TreeSet<Seat>(seats);
		Assert.assertEquals(seats.size(), set.size());
	}
	
}
