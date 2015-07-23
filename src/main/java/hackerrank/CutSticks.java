package hackerrank;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class CutSticks {
    static int Answer;

    public static void main(String args[]) throws Exception {
        /*
           The method below means that the program will read from input.txt, instead of standard(keyboard) input.
           To test your program, you may save input data in input.txt file,
           and call below method to read from the file when using nextInt() method.
           You may remove the comment symbols(//) in the below statement and use it.
           But before submission, you must remove the freopen function or rewrite comment symbols(//).
         */

        /*
           Make new scanner from standard input System.in, and read data.
         */
        Scanner sc = new Scanner(new FileInputStream("probs/Hackerrank/cut_sticks_1.txt"));
        //Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();
        List<Integer> sticks = new ArrayList<>(T);
        for (int i = 0; i < T; i++) {
            sticks.add(sc.nextInt());
        }

        while (sticks.size() > 1) {
            int low = sticks.get(0);
            for (Integer stick : sticks) {
                if (stick < low) low = stick;
            }
        }

        System.out.println(sticks);
    }
}
