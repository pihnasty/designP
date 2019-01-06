package format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class PredefinedFormatTest {

	private LocalDateTime now;
	private String dateString;

	@Test
	public void preDefinedConstant_BASIC_ISO_DATE() {
		dateString = now.format(DateTimeFormatter.BASIC_ISO_DATE);
		printOutDateString("BASIC_ISO_DATE");

	}

	@Test
	public void preDefinedConstant_ISO_DATE() {
		dateString = now.format(DateTimeFormatter.ISO_DATE);
		printOutDateString("ISO_DATE");
	}

	@Test
	public void preDefinedConstant_ISO_DATE_TIME() {
		dateString = now.format(DateTimeFormatter.ISO_DATE_TIME);
		printOutDateString("ISO_DATE_TIME");
	}

	@Test
	public void preDefinedConstant_ISO_LOCAL_DATE() {
		dateString = now.format(DateTimeFormatter.ISO_LOCAL_DATE);
		System.out.println("ISO_LOCAL_DATE");
	}

	@Test
	public void preDefinedConstant_ISO_LOCAL_DATE_TIME() {
		dateString = now.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println("ISO_LOCAL_DATE_TIME");
	}

	@Test
	public void preDefinedConstant_ISO_ORDINAL_DATE() {
		dateString = now.format(DateTimeFormatter.ISO_ORDINAL_DATE);
		System.out.println("ISO_ORDINAL_DATE");
	}

	@Test
	public void preDefinedConstant_ISO_WEEK_DATE() {
		dateString = now.format(DateTimeFormatter.ISO_WEEK_DATE);
		System.out.println("ISO_WEEK_DATE");
	}

	@Before
	public void setup() {
		now = LocalDateTime.now();
		System.out.println("now:" + now);
	}

	private void printOutDateString(String formatType) {
		System.out.println(formatType + ": " + dateString);
	}

}
