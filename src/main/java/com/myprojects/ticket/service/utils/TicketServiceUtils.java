package com.myprojects.ticket.service.utils;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.myprojects.ticket.service.enums.SeatType;

/**
 * This class contains utility methods
 *
 */
final public class TicketServiceUtils {
	
	private TicketServiceUtils() {
	}
	
	private static final String EMAIL_REGX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGX);

	/**
	 * Converts long to Base36 String
	 * @param n long number
	 * @return String base36 representation of given number
	 */
	public static String getBase36(long n) {
		String str = Long.toString(n, 36);
		return str.toUpperCase();
	}

	/**
	 * Calculates seat rank based on position of the seat from stage<br>
	 * This method calculates rank based on position and the sequence
	 * @param rowId of the seat
	 * @param colId of the seat
	 * @param maxRows maximum rows in the hall
	 * @param maxCols maximum columns in the hall
	 * @return int Rank of the seat
	 */
	public static int calculateSeatRank(final int rowId, final int colId, final int maxRows, final int maxCols) {
		int seatRank = 0;
		if (rowId % 2 == 0) {
			seatRank = rowId * maxRows + (maxCols - colId);
		} else {
			seatRank = rowId * maxRows + colId;
		}
		return seatRank;
	}

	/**
	 * Identifies the seat type based on below parameters
	 * @param rowId of the seat
	 * @param maxGoldRows maximum gold rows in the hall from stage
	 * @param maxSilverRows maximum silver rows in the hall from stage after gold rows
	 * @return SeatType
	 */
	public static SeatType identifySeatType(int rowId, int maxGoldRows, int maxSilverRows) {
		SeatType seatType = SeatType.BRONZE;
		if (rowId <= maxGoldRows) {
			seatType = SeatType.GOLD;
		} else if (rowId <= (maxGoldRows + maxSilverRows)) {
			seatType = SeatType.SILVER;
		}
		return seatType;
	}
	
	/**
	 * Validates email address
	 * @param email customer's email address 
	 * @return boolean
	 */
	public static boolean isValidEmail(String email) {
		boolean valid = false;
		if(StringUtils.isNotBlank(email)){
			valid = EMAIL_PATTERN.matcher(email).matches();
		}
		return valid;
	}
}
