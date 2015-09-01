/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful.

*/
package samsung;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
   As the name of the class should be Algorithm , using Algorithm.java as the filename is recommended.
   In any case, you can execute your program by running 'java Algorithm' command.
 */
class AssemblyLine {

    static int Answer;
    static int N = 50;
    static int[] partA = new int[N];
    static int[] partB = new int[N];
    static int[] line = new int[N * 2];

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20141209/sample_input_1.txt"));

        List<Integer> skip = new ArrayList<>();
        for(int test_case = 1; test_case <= 4; test_case++) {

            int parts = sc.nextInt();
            for (int i = 0; i < parts * 2; i++) {
                line[i] = sc.nextInt();
            }
            for (int i = 0; i < parts; i++) {
                partA[i] = sc.nextInt();
            }
            for (int i = 0; i <  parts; i++) {
                partB[i] = sc.nextInt();
            }

            /////////////////////////////////////////////////////////////////////////////////////////////
            boolean goodA = check(partA, parts, skip);
            boolean goodB = check(partB, parts, skip);
            Answer = goodA && goodB ? 1 : 0;

            skip.clear();
            partA = new int[N];
            partB = new int[N];
            line = new int[N * 2];
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println("#" + test_case + " " + Answer);
        }
    }

    public static boolean check(int[] p, int N, List skip) {
        int lastIdx = -1;
        boolean complete = true;
        for (int i = 0; i < N; i++) {
            int x = p[i];
            boolean exists = false;
            for (int j = 0; j < N*2; j++) {
                int y = line[j];
                if (!exists) {
                    exists = x == y && !skip.contains(j) && lastIdx < j;
                    if (exists) {
                        skip.add(j);
                        lastIdx = j;
                    }
                }
            }
            complete = exists;
            if (!complete) break;
        }
        return complete;
    }
}