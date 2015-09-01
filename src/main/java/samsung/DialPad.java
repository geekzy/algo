/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful.

*/
package samsung;

import java.io.FileInputStream;
import java.util.Scanner;

/*
   As the name of the class should be Algorithm , using Algorithm.java as the filename is recommended.
   In any case, you can execute your program by running 'java Algorithm' command.
 */
class DialPad {

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20141013/sample_input_1.txt"));

        int[][] pad = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9},
            {77, 0, 99}
        };

        int T = sc.nextInt();sc.nextLine();
        for(int test_case = 0; test_case < T; test_case++) {

            Answer = 0; // reset each case
            String line = sc.nextLine().trim();
            int[] five = {1, 1}; int lastDial = 5;
            /////////////////////////////////////////////////////////////////////////////////////////////
            for (int i = 0, dial = 0; i < line.length(); i++) {

                // parse input
                String d = line.substring(i, i+1);
                if ("#".equals(d)) dial = 99;
                else if ("*".equals(d)) dial = 77;
                else dial = Integer.parseInt(line.substring(i, i+1));

                if (lastDial == dial) continue;
                // find dial coord
                for (int y = 0; y < 4; y++) {
                    for (int x = 0; x < 3; x++) {
                        // count it
                        if (pad[y][x] == dial) {
                            int a = five[0] - y;
                            int b = five[1] - x;
                            five[0] = y; five[1] = x;

                            Answer += (a < 0 ? a*-1 : a) + (b < 0 ? b*-1 : b);
                            break;
                        }
                    }
                }
            }
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println(Answer);
        }
    }
}