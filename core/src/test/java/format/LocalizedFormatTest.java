package format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import org.junit.Before;
import org.junit.Test;

public class LocalizedFormatTest {

	private LocalDateTime now;

	@Before
	public void setup() {
		now = LocalDateTime.now();
		System.out.println("now:" + now);
	}

	@Test(expected=Exception.class)
	public void ofLocalizedDateTime_full_2() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		System.out.println("ofLocalizedDateTime_full:" + formatter.format(now));
	}

	//see https://bugs.openjdk.java.net/browse/JDK-8085887
	@Test(expected=Exception.class)
	public void ofLocalizedDateTime_full() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);
		System.out.println("ofLocalizedDateTime_full:" + now.format(formatter));
	}

	@Test(expected=Exception.class)
	public void ofLocalizedDateTime_long() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
		System.out.println("ofLocalizedDateTime_long:" + now.format(formatter));
	}

	@Test
	public void ofLocalizedDateTime_medium() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		System.out.println("ofLocalizedDateTime_medium:" + now.format(formatter));
	}

	@Test
	public void ofLocalizedDateTime_short() {
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
		System.out.println("ofLocalizedDateTime_short:" + now.format(formatter));
	}

}
