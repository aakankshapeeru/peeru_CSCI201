
package peeru_CSCI201_Assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Utility implements DateValidator {
	private DateTimeFormatter dateFormatter;

	public Utility(DateTimeFormatter dateFormatter) {
		this.dateFormatter = dateFormatter;
	}

	@Override
	public boolean isValid(String dateStr) {
		try {
			LocalDate.parse(dateStr, dateFormatter);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}

	public static String getTime(long startTime) {

		long currentTime = System.currentTimeMillis();
		long milliseconds = (currentTime - startTime);
		long seconds = 0;
		long minutes = 0;
		long hours = 0;
		while (milliseconds >= 1000) {
			milliseconds -= 1000;
			seconds += 1;
			if (seconds >= 60)
				minutes += 1;
			if (seconds == 60)
				seconds = 0;
			if (minutes >= 60)
				hours += 1;
			if (minutes == 60)
				minutes = 0;
		}
		String time = String.format("[%02d:%02d:%02d.%03d]", hours, minutes, seconds, milliseconds);
		return time;

	}

	// Referenced code from:
	// https://stackoverflow.com/questions/10874048/from-milliseconds-to-hour-minutes-seconds-and-milliseconds
}