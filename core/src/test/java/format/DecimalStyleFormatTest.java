package format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DecimalStyle;
import java.util.Locale;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DecimalStyleFormatTest {

	private LocalDateTime now;
	private DateTimeFormatter formatter;

	private static String DECIMALSTYLE_PERIOD="DecimalStyle[0+-.]";
	private static String DECIMALSTYLE_COMMA="DecimalStyle[0+-,]";

	@Test
	public void decimalStyle_standard() {
		DateTimeFormatter stdFormatter = formatter.withDecimalStyle(DecimalStyle.STANDARD);
		assertEquals(DECIMALSTYLE_PERIOD, stdFormatter.getDecimalStyle().toString());
		System.out.println("Standard:" + stdFormatter.format(now));
	}

	@Test
	public void decimalStyle_us() {
		DateTimeFormatter usFormatter = formatter.withDecimalStyle(DecimalStyle.of(Locale.US));
		assertEquals(DECIMALSTYLE_PERIOD, usFormatter.getDecimalStyle().toString());
		System.out.println("US:" + usFormatter.format(now));
	}

	@Test
	public void decimalStyle_german() {
		DateTimeFormatter germanFormatter = formatter.withDecimalStyle( DecimalStyle.of(Locale.GERMAN));
		assertEquals(DECIMALSTYLE_COMMA, germanFormatter.getDecimalStyle().toString());
		System.out.println("German:" + germanFormatter.format(now));
	}

	@Test
	public void decimalStyle_italy() {
		DateTimeFormatter germanFormatter = formatter.withDecimalStyle( DecimalStyle.of(Locale.ITALY));
		assertEquals(DECIMALSTYLE_COMMA, germanFormatter.getDecimalStyle().toString());
		System.out.println("Italy:" + germanFormatter.format(now));
	}

	@Test
	public void decimalStyle_france() {
		DateTimeFormatter germanFormatter = formatter.withDecimalStyle( DecimalStyle.of(Locale.FRANCE));
		assertEquals(DECIMALSTYLE_COMMA, germanFormatter.getDecimalStyle().toString());
		System.out.println("France:" + germanFormatter.format(now));
	}

	@Test
	public void decimalStyle_china() {
		DateTimeFormatter germanFormatter = formatter.withDecimalStyle( DecimalStyle.of(Locale.CHINA));
		assertEquals(DECIMALSTYLE_PERIOD, germanFormatter.getDecimalStyle().toString());
		System.out.println("China:" + germanFormatter.format(now));
	}

	@Before
	public void setup() {
		now = LocalDateTime.now();
		formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		System.out.println("now:" + now);
		System.out.println("now with ISO_LOCAL_DATE_TIME:" + formatter.format(now));
		System.out.println("Default decimalStyle:" + formatter.getDecimalStyle());
	}

	@Test
	@Ignore
	public void printOutAllAvailableLocalesDecimalStyle() {
		for (Locale localStyle : DecimalStyle.getAvailableLocales()) {
			DateTimeFormatter eFormatter = formatter.withDecimalStyle(DecimalStyle.of(localStyle));
			System.out.println(localStyle.getDisplayName() + "-" + localStyle.toString() + "-DecimalStyle:"
					+ eFormatter.withDecimalStyle(DecimalStyle.of(localStyle)).getDecimalStyle());
			System.out.println("*********");
		}
	}
}
