package samsung;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
class GraphCyclingPath {

    public static void main(String[] args) throws Throwable {
        GraphCyclingPath app = new GraphCyclingPath();
        app.start();
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/Problem_20150722/sample_input_1.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            for (int e = 0; e < E; e++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
            }
        }
    }
}
