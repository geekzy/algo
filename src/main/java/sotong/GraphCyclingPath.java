package sotong;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class GraphCyclingPath {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("probs/Problem_20150722/sample_input_1.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int V = sc.nextInt();

        int[] Answer = new int[N];
        int AnswerN = 0;

        for (int i = 0; i < V; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
        }
    }
}
