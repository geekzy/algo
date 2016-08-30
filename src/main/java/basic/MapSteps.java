package basic;

import java.io.FileInputStream;
import java.util.Scanner;

public class MapSteps {
    int steps;
    int R, C;
    int[][] map;
    int[][] stepped;
    int[] stepy = {-1, 1, 0, 0};
    int[] stepx = {0, 0, -1, 1};

    public void start() throws Throwable {
        System.setIn(new FileInputStream("probs/basic/map_steps.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            // setup data
            steps = 0;
            R = sc.nextInt();
            C = sc.nextInt();
            map = new int[R][C];
            stepped = new int[R][C];
            int sy = sc.nextInt();
            int sx = sc.nextInt();

            // setup map
            for (int y = 0; y < R; y++)
                for (int x = 0; x < C; x++)
                    map[y][x] = sc.nextInt();

            int total = walk(sy, sx);
            System.out.println("map #" + tc + " has " + total + " connected step(s) from coord " + sy + "," + sx);
            System.out.println();
        }
    }

    public int walk(int y, int x) {
        System.out.println("walking at " + y + "," + x);
        // mark stepped
        stepped[y][x] = 1;
        // count step
        steps++;
        // walking
        for (int s = 0; s < 4; s++)
            if (valid(y + stepy[s], x + stepx[s]))
                walk(y + stepy[s], x + stepx[s]);

        return steps;
    }

    public boolean valid(int y, int x) {
        return y >= 0 && x >= 0 && y < R && x < C && map[y][x] == 1 && stepped[y][x] == 0;
    }

    public static void main(String[] args) throws Throwable {
        MapSteps app = new MapSteps();
        app.start();
    }
}