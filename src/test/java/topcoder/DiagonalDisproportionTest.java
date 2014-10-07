package topcoder;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class DiagonalDisproportionTest {

	static DiagonalDisproportion app;

	@BeforeClass
	public static void before() {
		app = new DiagonalDisproportion();
	}

	@Test
	public void getDisproportionTest_1() {
		String[] input = {"190","828","373"};
		int disproportion = app.getDisproportion(input);

		assertEquals("Invalid disproportion", 1, disproportion);
	}

	@Test
	public void getDisproportionTest_2() {
		String[] input = {"9000","0120","0000","9000"};
		int disproportion = app.getDisproportion(input);

		assertEquals("Invalid disproportion", -1, disproportion);
	}

	@Test
	public void getDisproportionTest_3() {
		String[] input = {"6"};
		int disproportion = app.getDisproportion(input);

		assertEquals("Invalid disproportion", 0, disproportion);
	}

	@Test
	public void getDisproportionTest_4() {
		String[] input = {"7748297018","8395414567","7006199788","5446757413","2972498628","0508396790","9986085827","2386063041","5687189519","7729785238"};
		int disproportion = app.getDisproportion(input);

		assertEquals("Invalid disproportion", -24, disproportion);
	}
}