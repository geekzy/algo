/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful.

*/
package sotong;

import java.util.Scanner;
import java.io.FileInputStream;

/*
   As the name of the class should be Algorithm , using Algorithm.java as the filename is recommended.
   In any case, you can execute your program by running 'java Algorithm' command.
 */
class CutDeck {

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20141013/sample_input_2.txt"));

        int T = sc.nextInt();sc.nextLine();
        for(int test_case = 0; test_case < T; test_case++) {

            String line = sc.nextLine().trim();
            /////////////////////////////////////////////////////////////////////////////////////////////
            int count = 0; int x = 0; int[] idx = new int[line.length()]; int[] max = new int[line.length()];
            Answer = 0; // reset each case
            for (int i = 0; i < line.length(); i++) {
                char card = line.charAt(i);

                // mark first occurance
                if (card == 'B' && count == 0) {
                    idx[x] = i;
                    count++;
                }
                // increse count
                else if (card == 'B') {
                    count++;
                }
                // record max and reset on R
                else if (card == 'R' && count > 0) {
                    max[x++] = count;
                    count = 0;
                } // just skip on another R
                //System.out.print(String.format("[%c]count:%d|x:%d#", card, count, x));
            }

            int biggest = 0; int candidate = 0;
            for (int j = 0; j < max.length; j++) {
                if (max[j] > biggest) {
                    candidate = j;
                    biggest = max[j];
                }
            }
            Answer = idx[candidate];
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println(Answer);
        }
    }
}