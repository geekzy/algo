package sadgewick;

import java.util.Scanner;
import java.io.FileInputStream;

/**
 * Union Find from Java Algorithm part 1 @Coursera
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class UnionFind {

    class QuickFindUF {
        int[] id;
        int count;

        public QuickFindUF(int N) {
            id = new int[N];
            count = N;
            for (int n = 0; n < N; n++) id[n] = n;
        }

        boolean connected(int p, int q) { return id[p] == id[q]; }

        void union(int p, int q) {
            if (connected(p, q)) return;

            int idP = id[p];
            int idQ = id[q];

            System.out.print("id[ ");
            for (int i = 0; i < id.length; i++) {
                if (id[i] == idP) id[i] = idQ;
                System.out.print(id[i] + " ");
            }
            System.out.print("]");
            count--;
        }
    }

    class QuickUnionUF {
        int[] id;

        public QuickUnionUF(int N) {
            id = new int[N];
            for (int n = 0; n < N; n++) id[n] = n;
        }

        int root(int x) {
            while(x != id[x]) x = id[x];
            return x;
        }

        boolean connected(int p, int q) {
            return root(p) == root(q);
        }

        void union(int p, int q) {
            int idP = root(p);
            int idQ = root(q);
            // root of q is the root of p
            id[idP] = idQ;
        }
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/sadgewick/tinyUF.txt"));
        Scanner sc = new Scanner(System.in);

        long start = System.currentTimeMillis();
        int N = sc.nextInt();
        //QuickFindUF qf = new QuickFindUF(N);
        QuickUnionUF qu = new QuickUnionUF(N);
        for (int n = 0; n < N; n++) {
            int p = sc.nextInt();
            int q = sc.nextInt();

            System.out.print(p + " -> " + q + " ");
            //qf.union(p, q);
            qu.union(p, q);
            System.out.println();
        }
        //System.out.println("Components: " + qf.count);
        System.out.println("6 -> 1 :" + (qu.connected(6, 1) ? "" : " NOT") + " Connected!");
        System.out.println("9 -> 0 :" + (qu.connected(9, 0) ? "" : " NOT") + " Connected!");
        System.out.println("took " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void main(String[] args) throws Throwable {
        UnionFind app = new UnionFind();
        app.start();
    }
}
