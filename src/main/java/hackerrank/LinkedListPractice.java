package hackerrank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class LinkedListPractice {

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("probs/Hackerrank/data_structure_1.txt"));
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        Graph<Integer> g = new Graph<>();
        g.insertHead(7);
        for (int i = 0; i < N; i++) {
            int data = sc.nextInt();
            int pos = sc.nextInt();

            g.insertNth(data, pos);
        }

        g.reverse();
        //g.deleteNtn(4);
        for (Integer d : g) {
            System.out.println(d);
        }
    }

    private static class Graph<T> implements Iterable<T> {
        Node head;

        public void insertHead(T data) {
            Node x = new Node();
            x.data = data;
            x.next = head;
            head = x;
        }

        public void reverse() {
            Node last = null;
            int i = 0;
            for (Node n = head; n != null; n = n.next) {
                Node x = new Node();
                x.data = n.data;

                if (i == 0) {
                    x.next = null;
                } else x.next = last;

                last = x;
                i++;
            }
            head = last;
        }

        public void deleteNtn(int position) {
            int i = 0;
            for (Node n = head; n != null; n = n.next) {
                if (i == position - 1 || position == 0) {
                    // head
                    if (position == 0) {
                        head = n.next;
                    }
                    // not head
                    else {
                        if (n.next.next == null) {
                            n.next = null;
                        } else n.next = n.next.next;
                    }
                    break;
                }
                i++;
            }
        }

        public void insertNth(T data, int position) {
            int i = 0;
            for (Node n = head; n != null; n = n.next) {
                if (i == position - 1 || position == 0) {
                    Node x = new Node();
                    x.data = data;
                    // head
                    if (position == 0) {
                        x.next = n;
                        head = x;
                    }
                    // not head
                    else {
                        Node t = new Node();
                        t.data = n.data;
                        t.next = n.next;

                        n.next = x;
                        x.next = t.next;
                    }
                    break;
                }
                i++;
            }
        }

        @Override
        public Iterator<T> iterator() {
            return new GraphIterator();
        }

        private class GraphIterator implements Iterator<T> {

            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.data;
                current = current.next;
                return item;
            }

        }

        private class Node {
            T data;
            Node next;
        }
    }
}
