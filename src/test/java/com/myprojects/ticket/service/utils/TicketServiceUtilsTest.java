package com.myprojects.ticket.service.utils;

import org.junit.Assert;
import org.junit.Test;

import com.myprojects.ticket.service.enums.SeatType;

public class TicketServiceUtilsTest {
	
	@Test
	public void testGetBase36() {
		Assert.assertEquals("0", TicketServiceUtils.getBase36(0));
		Assert.assertEquals("1", TicketServiceUtils.getBase36(1));
		Assert.assertEquals("9", TicketServiceUtils.getBase36(9));
		Assert.assertEquals("A", TicketServiceUtils.getBase36(10));
		Assert.assertEquals("Z", TicketServiceUtils.getBase36(35));
		Assert.assertEquals("10", TicketServiceUtils.getBase36(36));
		Assert.assertEquals("11", TicketServiceUtils.getBase36(37));
		Assert.assertEquals("1Z", TicketServiceUtils.getBase36(71));
		Assert.assertEquals("20", TicketServiceUtils.getBase36(72));
	}
	
	@Test
	public void testSeatRank() {
		int maxRows = 4;
		int maxCols = 3;
		int rank1 = TicketServiceUtils.calculateSeatRank(1, 1, maxRows, maxCols);
		int rank2 = TicketServiceUtils.calculateSeatRank(1, 1, maxRows, maxCols);
		Assert.assertEquals(rank1, rank2);
		
		rank1 = TicketServiceUtils.calculateSeatRank(1, 1, maxRows, maxCols);
		rank2 = TicketServiceUtils.calculateSeatRank(1, 2, maxRows, maxCols);
		Assert.assertTrue(rank1 < rank2);
		
		rank1 = TicketServiceUtils.calculateSeatRank(1, 3, maxRows, maxCols);
		rank2 = TicketServiceUtils.calculateSeatRank(1, 2, maxRows, maxCols);
		Assert.assertTrue(rank1 > rank2);
		
		rank1 = TicketServiceUtils.calculateSeatRank(2, 3, maxRows, maxCols);
		rank2 = TicketServiceUtils.calculateSeatRank(2, 1, maxRows, maxCols);
		Assert.assertTrue(rank1 < rank2);
	}
	
	@Test
	public void testIdentifySeatType() {
		int maxGoldRows = 3;
		int maxSilverRows = 5;
		
		Assert.assertEquals(SeatType.GOLD, TicketServiceUtils.identifySeatType(1, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.GOLD, TicketServiceUtils.identifySeatType(2, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.GOLD, TicketServiceUtils.identifySeatType(3, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.SILVER, TicketServiceUtils.identifySeatType(4, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.SILVER, TicketServiceUtils.identifySeatType(5, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.SILVER, TicketServiceUtils.identifySeatType(6, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.SILVER, TicketServiceUtils.identifySeatType(7, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.SILVER, TicketServiceUtils.identifySeatType(8, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.BRONZE, TicketServiceUtils.identifySeatType(9, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.BRONZE, TicketServiceUtils.identifySeatType(10, maxGoldRows, maxSilverRows));
		Assert.assertEquals(SeatType.BRONZE, TicketServiceUtils.identifySeatType(15, maxGoldRows, maxSilverRows));
	}
	
	@Test
	public void testIsValidEmail() {
		Assert.assertFalse(TicketServiceUtils.isValidEmail(null));
		Assert.assertFalse(TicketServiceUtils.isValidEmail(""));
		Assert.assertFalse(TicketServiceUtils.isValidEmail("test.test"));
		Assert.assertTrue(TicketServiceUtils.isValidEmail("test.test123@test.org"));
		Assert.assertTrue(TicketServiceUtils.isValidEmail("test.test.123@test.org"));
	}
}
