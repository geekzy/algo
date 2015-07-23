package hackerrank;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class LoveLetterMistery {

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
        Scanner sc = new Scanner(new FileInputStream("probs/Hackerrank/love_letter_1.txt"));
        //Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        sc.nextLine();

        for (int test_case = 0; test_case < T; test_case++) {
            StringBuilder sb = new StringBuilder(sc.nextLine());
            int steps = intoPalindrom(sb.toString());

            // Print the answer to standard output(screen).
            System.out.println(steps);
        }
    }

    private static int intoPalindrom(String line) {
        int steps = 0;
        if (!isPalindrome(line)) {
        }
        return steps;
    }

    private static boolean isPalindrome(String word) {
        return (new StringBuilder(word)).reverse().toString().equals(word);
    }
}
