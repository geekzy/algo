/*

In Practice, You should use the statndard input/output
in order to receive a score properly.
Do not use file input and output. Please be very careful.

*/
package samsung;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
   As the name of the class should be Algorithm , using Algorithm.java as the filename is recommended.
   In any case, you can execute your program by running 'java Algorithm' command.
 */
class DistinctNumbers {

    static int Answer = 0;

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20141008/sample_input_1.txt"));
        //Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int test_case = 0; test_case < T; test_case++) {

            int N = sc.nextInt();

            /////////////////////////////////////////////////////////////////////////////////////////////
            String n = String.valueOf(N);
            Set<Character> unique = new HashSet<Character>(n.length());
            for (int i = 0; i < n.length(); i++) {
                unique.add(n.charAt(i));
            }

            Answer = unique.size();
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println(Answer);
        }
    }
}