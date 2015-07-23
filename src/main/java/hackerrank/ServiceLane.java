package hackerrank;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Hackerrank - warmup - Service Lane
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class ServiceLane {
    static int Answer;
    static int[] lanes;

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
        Scanner sc = new Scanner(new FileInputStream("probs/Hackerrank/service_lane_1.txt"));
        //Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int T = sc.nextInt();

        lanes = new int[N];
        for (int lane = 0; lane < N; lane++) {
            lanes[lane] = sc.nextInt();
        }

        for (int test_case = 0; test_case < T; test_case++) {
            int enter = sc.nextInt();
            int exit = sc.nextInt();
            Answer = lanes[enter];
            for (int i = 0; i < (exit - enter) + 1; i++) {
                if (lanes[i + enter] < Answer) Answer = lanes[i + enter];
            }
            // Print the answer to standard output(screen).
            System.out.println(Answer);
        }
    }
}
