package week19;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class WrittingWordsTest {
    private static WrittingWords sWrittingWords;

    @Before
    public void setup() {
        sWrittingWords = new WrittingWords();
    }

    @Test
    public void testWrite() {
        // Config
        // -------
        String word1 = "A";
        String word2 = "ABC";
        String word3 = "VAMOSGIMNASIA";
        String word4 = "TOPCODER";
        String word5 = "singleroundmatch";
        String word6 = "ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ";

        // Test
        // -------
        int taps1 = sWrittingWords.write(word1);
        int taps2 = sWrittingWords.write(word2);
        int taps3 = sWrittingWords.write(word3);
        int taps4 = sWrittingWords.write(word4);
        int taps5 = sWrittingWords.write(word5);
        int taps6 = sWrittingWords.write(word6);

        // Checks
        // -------
        assertEquals(1, taps1);
        assertEquals(6, taps2);
        assertEquals(143, taps3);
        assertEquals(96, taps4);
        assertEquals(183, taps5);
        assertEquals(1300, taps6);
    }
}
