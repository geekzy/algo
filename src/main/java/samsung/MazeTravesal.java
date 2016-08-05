package samsung;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Ade Imam Kurniawan (ade.imam@samsung.com) SRIN
 */
public class MazeTravesal {

    private int R;
    private int C;
    private int goldFound = 0;
    private int[][] maze = new int[20][20];
    private int[][] visited = new int[20][20];
    private int[] nextY = new int[]{ -1, +1,  0,  0 };
    private int[] nextX = new int[]{  0,  0, -1, +1 };

    public static void main(String[] args) throws Throwable {
        MazeTravesal app = new MazeTravesal();
        app.start();
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/basic/sample_input_1.txt"));
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        int sx = sc.nextInt();
        int sy = sc.nextInt();

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) maze[y][x] = sc.nextInt();
        }

        findGold(sx, sy);
        System.out.println("Found Gold: " + goldFound);
    }

    private void findGold(int y, int x) {
        // each call would be a gold
        goldFound++;
        // mark visited
        visited[y][x] = 1;

        for (int n = 0; n < 4; n++)
            if (valid(y + nextY[n], x + nextX[n])) findGold(y + nextY[n], x + nextX[n]);
    }

    private boolean valid(int y, int x) {
        return x >= 0 && x < C && y >= 0 && y < R && visited[y][x] == 0 && maze[y][x] == 1;
    }
}
