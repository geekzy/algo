package topcoder;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaroStringTest {

    static TaroString app;

    @BeforeClass
    public static void beforClass() {
        app = new TaroString();
    }

    @Test
    public void testGetAnswer_1() {
        String taro = "XCYAZTX";
        String check = app.getAnswer(taro);

        assertEquals("Not a Taro", "Possible", check);
    }

    @Test
    public void testGetAnswer_2() {
        String taro = "CTA";
        String check = app.getAnswer(taro);

        assertEquals("Not a Taro", "Impossible", check);
    }

    @Test
    public void testGetAnswer_3() {
        String taro = "ACBBAT";
        String check = app.getAnswer(taro);

        assertEquals("Not a Taro", "Impossible", check);
    }

    @Test
    public void testGetAnswer_4() {
        String taro = "SGHDJHFIOPUFUHCHIOJBHAUINUIT";
        String check = app.getAnswer(taro);

        assertEquals("Not a Taro", "Possible", check);
    }

    @Test
    public void testGetAnswer_5() {
        String taro = "CCCATT";
        String check = app.getAnswer(taro);

        assertEquals("Not a Taro", "Impossible", check);
    }
}