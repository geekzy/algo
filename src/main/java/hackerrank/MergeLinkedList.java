package hackerrank;

import java.io.FileInputStream;
import java.util.Scanner;

public class MergeLinkedList {
    class Node {
        int data;
        Node next;
    }

    class Bag {
        Node head;
        int N;

        void addSortedNode(int data) {
            if (head != null) {
                for (Node n = head; n != null; n = n.next) {
                    if (data <= n.data) {
                        Node x = n;
                        n = new Node();
                        n.data = data;
                        n.next = x;
                        break;
                    }
                    else if (n.next == null) {
                        Node x = new Node();
                        x.data = data;
                        n.next = x;
                        break;
                    }
                }
            }
            else {
                head = new Node();
                head.data = data;
            }

            N++;
        }
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/hackerrank/merge_linked_list.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 1; tc <= T; tc++) {
            Bag bag = new Bag();

            int X = sc.nextInt();
            for (int x = 0; x < X; x++) {
                int data = sc.nextInt();
                bag.addSortedNode(data);
            }

            int Y = sc.nextInt();
            for (int y = 0; y < Y; y++) {
                int data = sc.nextInt();
                bag.addSortedNode(data);
            }

            for (Node n = bag.head; n != null; n = n.next) {
                System.out.print(n.data + "->");
            }
            System.out.println("NULL");
        }
    }

    public static void main(String[] args) throws Throwable {
        MergeLinkedList app = new MergeLinkedList();
        app.start();
    }
}