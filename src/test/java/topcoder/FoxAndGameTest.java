package topcoder;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class FoxAndGameTest {

	static FoxAndGame app;

	@BeforeClass
	public static void before() {
		app = new FoxAndGame();
	}

	@Test
	public void testCountStars_1() {
		String[] results = {"ooo", "ooo"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 6, stars);
	}

	@Test
	public void testCountStars_2() {
		String[] results = {"ooo", "oo-", "o--"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 6, stars);
	}

	@Test
	public void testCountStars_3() {
		String[] results = {"ooo", "---", "oo-", "---", "o--"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 6, stars);
	}

	@Test
	public void testCountStars_4() {
		String[] results = {"o--", "o--", "o--", "ooo", "---"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 6, stars);
	}

	@Test
	public void testCountStars_5() {
		String[] results = {"---", "o--", "oo-", "ooo", "ooo", "oo-", "o--", "---"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 12, stars);
	}

	@Test
	public void testCountStars_6() {
		String[] results = {"---", "---", "---", "---", "---", "---"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 0, stars);
	}

	@Test
	public void testCountStars_7() {
		String[] results = {"oo-"};
		int stars = app.countStars(results);

		assertEquals("Invalid stars counting", 2, stars);
	}
}