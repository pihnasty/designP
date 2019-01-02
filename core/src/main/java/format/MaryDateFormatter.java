package format;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class MaryDateFormatter {

	static DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder();

	public static DateTimeFormatter getMaryDateFormatter() {
		return builder.appendLiteral("Day is:").appendValue(ChronoField.DAY_OF_MONTH).appendLiteral(", Month is:")
				.appendValue(ChronoField.MONTH_OF_YEAR).appendLiteral(", Year is:").appendValue(ChronoField.YEAR)
				.appendLiteral(" with the time").appendText(ChronoField.HOUR_OF_DAY).appendLiteral(":")
				.appendValue(ChronoField.MINUTE_OF_HOUR).toFormatter();

	}

}
