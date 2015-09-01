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
class TaroString {

    static boolean Answer;

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20140421/sample_input_1.txt"));

        int T = sc.nextInt();sc.nextLine();
        System.out.println("Total test cases: " + T);
        for(int test_case = 0; test_case < T; test_case++) {

            String line = sc.nextLine();
            System.out.print("Checking on [" + line + "]: ");
            /////////////////////////////////////////////////////////////////////////////////////////////
            Answer = checkTaroShort(line);
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            System.out.println(Answer ? "Possible" : "Impossible");
        }
    }

    public static boolean checkTaroShort(String line) {
        return line.matches("[A-Z]*C[^A&&[A-Z]]+A[^T&&[A-Z]]+T[A-Z]*");
    }

    public static boolean checkTaroLong(String line) {
        int cIdx = 0, aIdx = 0, tIdx = 0;
        for (int i = 0; i < line.length(); i++) {
            char now = line.charAt(i);

            if (now == 'C') { cIdx = i; }
            if (now == 'A') { aIdx = i; }
            if (now == 'T') { tIdx = i; }
        }

        return (aIdx - cIdx) > 1 && (tIdx - aIdx) > 1 && tIdx > cIdx && tIdx > aIdx;
    }
}