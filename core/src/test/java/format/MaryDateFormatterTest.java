package format;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

public class MaryDateFormatterTest {

	private LocalDateTime now;

	@Test
	public void customizedDateFormatter() {
		String text = now.format(MaryDateFormatter.getMaryDateFormatter());
		System.out.println("Mary Date formatted:" + text);
	}

	@Before
	public void setup() {
		now = LocalDateTime.now();
		System.out.println("now:" + now);
	}

}
