package sadgewick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Ade Imam Kurniawan (ade.imam@samsung.com) SRIN
 */
public class LinkedList {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("probs/sadgewick/linkedlist_struct_1.txt"));
        //Scanner sc = new Scanner(System.in);

        Stack<Integer> stack = new Stack<>();
        int T = sc.nextInt();
        while(T > 0) {
            int item = sc.nextInt();
            stack.push(item);
            T--;
        }

        sc = new Scanner(new FileInputStream("probs/sadgewick/linkedlist_struct_1.txt"));
        Queue<Integer> queue = new Queue<>();
        T = sc.nextInt();
        while(T > 0) {
            int item = sc.nextInt();
            queue.enqueue(item);
            T--;
        }

        sc = new Scanner(new FileInputStream("probs/sadgewick/linkedlist_struct_1.txt"));
        Bag<Integer> bag = new Bag<>();
        T = sc.nextInt();
        while(T > 0) {
            int item = sc.nextInt();
            bag.add(item);
            T--;
        }

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println("Stack [" + stack.size() + "]:");
        for (Integer item : stack) {
            System.out.println(item);
        }

        System.out.println();

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("Queue [" + queue.size() + "]:");
        for (Integer item : queue) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("Bag [" + bag.size() + "]:");
        for (Integer item : bag) {
            System.out.println(item);
        }
    }

    @SuppressWarnings("unused")
    private static class Stack<Item> implements Iterable<Item> {
        private Node first;
        private int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void push(Item item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        public Item pop() {
            Item item = first.item;
            first = first.next;
            N--;
            return item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new StackIterator();
        }

        private class StackIterator implements Iterator<Item> {
            private Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                Item item = node.item;
                node = node.next;
                return item;
            }
        }

        private class Node {
            Item item;
            Node next;
        }
    }

    @SuppressWarnings("unused")
    public static class Queue<Item> implements Iterable<Item> {
        private Node first;
        private Node last;
        private int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void enqueue(Item item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        public Item dequeue() {
            Item item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new QueueIterator();
        }

        private class QueueIterator implements Iterator<Item> {
            private Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                Item item = node.item;
                node = node.next;
                return item;
            }
        }

        private class Node {
            Item item;
            Node next;
        }
    }

    @SuppressWarnings("unused")
    private static class Bag<Item> implements Iterable<Item> {
        private Node first;
        private int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void add(Item item) {
            Node node = first;
            first = new Node();
            first.item = item;
            first.next = node;
            N++;
        }

        @Override
        public Iterator<Item> iterator() {
            return new BagIterator();
        }

        private class BagIterator implements Iterator<Item> {
            private Node node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public Item next() {
                Item item = node.item;
                node = node.next;
                return item;
            }
        }

        private class Node {
            Item item;
            Node next;
        }
    }
}
