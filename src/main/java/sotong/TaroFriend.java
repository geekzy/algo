/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful.

*/
package sotong;

import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

/*
   As the name of the class should be Algorithm , using Algorithm.java as the filename is recommended.
   In any case, you can execute your program by running 'java Algorithm' command.
 */
class TaroFriend {

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20140421/sample_input_2.txt"));

        int T = sc.nextInt();sc.nextLine();
        for(int test_case = 0; test_case < T; test_case++) {

            String line = sc.nextLine();
            String[] currCoords = line.trim().split(" ");

            int movements = sc.nextInt(); sc.nextLine();
            /////////////////////////////////////////////////////////////////////////////////////////////
            int[] coords = new int[currCoords.length];
            for (int i = 0; i < currCoords.length; i++) {
                int plus = 0, min = 0;
                if (movements > 0) {
                    plus = Integer.parseInt(currCoords[i]) + movements;
                    min = Integer.parseInt(currCoords[i]) - movements;
                }
                int x = 0-plus < 0 ? (0-plus)*-1 : 0-plus;
                int y = 0-min < 0 ? (0-min)*-1 : 0-min;
                if (x < y) coords[i] = plus;
                else coords[i] = min;
            }
            Arrays.sort(coords);
            Answer = coords[coords.length-1] - coords[0];
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println(Answer);
        }
    }
}