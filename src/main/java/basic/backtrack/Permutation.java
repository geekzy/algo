package basic.backtrack;

import java.io.FileInputStream;
import java.util.Scanner;

public class Permutation {
    private int[] strPos;
    private int[] usedPos;
    private int total;

    public void start() throws Throwable {
        System.setIn(new FileInputStream("probs/basic/permutation.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            long startTime = System.currentTimeMillis();
            // algorithm here
            String word = sc.next();
            int n = word.length();
            strPos = new int[n];
            usedPos = new int[n];
            total = 0;

            permute(0, n);
            System.out.println("#" + tc + "- " + word + " has " + total + " combination(s).");
            long endTime = System.currentTimeMillis();
            System.out.println("done in " + (endTime - startTime) + "ms");
            System.out.println();
        }

        sc.close();
    }

    private void permute(int pos, int limit) {
        if (pos == limit) total += 1; // one combination is set, record total
        else {
            for (int i = 0; i < limit; i++) {
                // make sure no repeat
                if (usedPos[i] == 0) {
                    // update values
                    usedPos[i] = 1;  // mark position has been used
                    strPos[pos] = i; // mark position to be used

                    // recurse
                    permute(pos + 1, limit);

                    // backtrack
                    usedPos[i] = 0;
                }
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        Permutation app = new Permutation();
        app.start();
    }
}