package hackerrank;

import java.io.FileInputStream;
import java.util.Scanner;

public class TwoLinkedList {

    class Node {
        int data;
        Node next;
    }

    class Bag {
        Node head;
        int N;

        void addNode(int data) {
            Node n = head;
            head = new Node();
            head.data = data;
            head.next = n;
            N++;
        }
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/hackerrank/two_linked_list.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            int I = sc.nextInt();
            Bag one = new Bag();
            for (int n = 0; n < I; n++) {
                int x = sc.nextInt();
                one.addNode(x);
            }

            int J = sc.nextInt();
            Bag two = new Bag();
            for (int n = 0; n < J; n++) {
                int x = sc.nextInt();
                two.addNode(x);
            }

            int equality = 0;
            if (one.N == two.N) equality = CompareList(one.head, two.head);;

            System.out.println(equality);
        }
    }

    boolean nullNode(String n) {
        return "NULL".equals(n.trim());
    }

    int CompareList(Node one, Node two) {
        for (Node x = one, y = two; x != null || y != null; x = x.next, y = y.next) {
            if (x == null || y == null) return 0;
            if (x.data != y.data) return 0;
        }
        return 1;
    }

    public static void main(String[] args) throws Throwable {
        TwoLinkedList app = new TwoLinkedList();
        app.start();
    }
}