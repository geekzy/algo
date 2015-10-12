package hackerrank;

import java.io.FileInputStream;
import java.util.Scanner;

public class AngryProfessor {

    public void start() throws Throwable {
        System.setIn(new FileInputStream("probs/Hackerrank/angry_professor.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt();
            int K = sc.nextInt();

            int ontime = 0;
            for (int n = 0; n < N; n++) {
                int X = sc.nextInt();
                ontime += X <= 0 ? 1 : 0;
            }

            if (ontime < K) System.out.println("YES");
            else System.out.println("NO");
        }
    }

    public static void main(String[] args) throws Throwable {
        AngryProfessor app = new AngryProfessor();
        app.start();
    }
}