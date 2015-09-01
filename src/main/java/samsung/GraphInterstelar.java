package samsung;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class GraphInterstelar {

    public static void main(String[] args) throws Throwable {
        GraphInterstelar app = new GraphInterstelar();
        app.start();
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/Problem_20150901/sample_input_1.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
        }
    }
}
