package samsung;

/**
 * @author Ade Imam Kurniawan (ade.imam@samsung.com) SRIN
 */
public class BasicsAlgorithm {
    static int limit = 3;
    static int[] elmC = new int[100];
    static int[] elmP = new int[100];
    static int[] used = new int[100];

    static int fib(int n) {
        if (n <= 2) return 1;
        else return fib(n - 2) + fib(n - 1);
    }

    static int pow(int x, int y) {
        if (y == 0) return 1;
        else return x * pow(x, y - 1);
    }

    static void combination(int pos) {
        if (pos > limit) printCombination(elmC);
        else {
            for (int i = 1; i <= limit; i++) {
                elmC[pos] = i;
                combination(pos + 1);
            }
        }
    }

    static void permutation(int pos) {
        if (pos > limit) printCombination(elmP);
        else {
            for (int i = 1; i <= limit; i++) {
                if (used[i] == 0) {
                    used[i] = 1;
                    elmP[pos] = i;
                    permutation(pos + 1);
                    used[i] = 0;
                }
            }
        }
    }

    private static void printCombination(int[] elm) {
        for (int n = 1; n <= limit; n++) {
            System.out.print(elm[n] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("fig(3) is " + fib(3));
        System.out.println("pow(2, 4) is " + pow(2, 5));
        System.out.println("combination(3) are:");
        combination(1);
        System.out.println("permutation(3) are:");
        permutation(1);
    }
}
