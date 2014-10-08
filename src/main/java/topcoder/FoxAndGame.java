package topcoder;

public class FoxAndGame {

    public int countStars(String[] results) {

        int stars = 0;
        for (int i = 0; i < results.length; i++) {
            stars += results[i].replace("-", "").length();
        }

        return stars;
    }
}