package inventory.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String dateToString(Date date) {
		// Convert Create Date from String to TimeStamp to insert Invoice to db
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static String currentMillisToDateTimeString(long currentMilliseconds) {
		// Convert Create Date from String to TimeStamp to insert Invoice to db
		Date date = new Date(currentMilliseconds);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss yyyy-MM-dd");
		return sdf.format(date);
	}
}
