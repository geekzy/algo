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
class AnagramCheck {

    static boolean Answer = false;
    static String[] words;

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
        Scanner sc = new Scanner(new FileInputStream("probs/Problem_20141008/sample_input_3.txt"));
        //Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();sc.nextLine();
        for(int test_case = 0; test_case < T; test_case++) {

            String line = sc.nextLine();
            words = line.split(" ");
            /////////////////////////////////////////////////////////////////////////////////////////////
            for (int i = 0; i < words.length; i++) {
                String[] sorted = words[i].split("(?!^)");
                Arrays.sort(sorted);
                StringBuilder buff = new StringBuilder();
                for (String s : sorted) {
                    buff.append(s);
                }
                words[i] = buff.toString();
            }
            Answer = words[0].equals(words[1]);
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to standard output(screen).
            if(Answer)
                System.out.println("anagram");
            else
                System.out.println("not anagram");
        }
    }
}