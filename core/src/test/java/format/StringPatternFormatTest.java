package format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

public class StringPatternFormatTest {

	private LocalDateTime now;

	@Before
	public void setup() {
		now = LocalDateTime.now();
		System.out.println("now:" + now);
	}

	@Test
	public void stringPattern_1() {
		String text = now.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
		System.out.println("String formatted:" + text);
	}

	@Test
	public void stringPattern_2() {
		String str = "2018-12-21 12:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		System.out.println("dateTime:" + dateTime);
		System.out.println("dateTime with formatter:" + dateTime.format(formatter));
	}

}
