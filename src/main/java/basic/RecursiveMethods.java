package basic;

import java.io.FileInputStream;
import java.util.Scanner;

public class RecursiveMethods {

    public void start() throws Throwable {
        System.setIn(new FileInputStream("probs/basic/recursive_methods.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            int num = sc.nextInt();

            System.out.println("TN: " + tringularNum(num));
            System.out.println();
        }
    }

    public int tringularNum(int number) {
        System.out.println("Executing tringularNum(" + number + ")");

        if (number == 1) {
            System.out.println("Returning 1");
            return 1;
        } else {
            int result = number + tringularNum(number - 1);
            System.out.print("Returning " + result);
            System.out.println(" : " + number + " + tringularNum(" + number + "-1)");

            return result;
        }
    }

    public static void main(String[] args) throws Throwable {
        RecursiveMethods app = new RecursiveMethods();
        app.start();
    }
}