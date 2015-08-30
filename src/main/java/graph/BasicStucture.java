package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings("unused")
public class BasicStucture {

    private class Stack<T> implements Iterable<T> {
        Node top;
        int N;

        public boolean isEmpty() { return top == null; }
        public int size() { return N; }

        public void push(T item) {
            Node oldTop = top;
            top = new Node();
            top.item = item;
            top.next = oldTop;
            N++;
        }

        public T pop() {
            T item = top.item;
            top = top.next;
            N--;
            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new StackIterator();
        }

        private class StackIterator implements Iterator<T> {
            Node current = top;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
            }
        }

        private class Node {
            T item;
            Node next;
        }
    }

    private class Queue<T> implements Iterable<T> {

        Node first;
        Node last;
        int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void enqueue(T item) {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            if (isEmpty()) first = last;
            else oldLast.next = last;
            N++;
        }

        public T dequeue() {
            T item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new QueueIterator();
        }

        private class QueueIterator implements Iterator<T> {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
            }
        }

        private class Node {
            T item;
            Node next;
        }
    }

    private class Bag<T> implements Iterable<T> {
        Node first;
        int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void add(T item) {
            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            N++;
        }

        @Override
        public Iterator<T> iterator() {
            return new BagIterator();
        }

        private class BagIterator implements Iterator<T> {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
            }
        }

        private class Node {
            T item;
            Node next;
        }
    }

    private class Clazz {
        final Integer item;

        private Clazz(Integer item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return "Containing item: " + item;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        BasicStucture app = new BasicStucture();
        app.start();
    }

    private void start() throws FileNotFoundException {
        System.setIn(new FileInputStream("probs/graph/data_1.txt"));
        Scanner sc = new Scanner(System.in);

        ///////////////////////////////////////////////////////////////////////
        //                          Algorithm Test                           //
        ///////////////////////////////////////////////////////////////////////
        Stack<Integer> stack = new Stack<>();
        Queue<String> queue = new Queue<>();
        Bag<Clazz> bag = new Bag<>();

        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            Integer item = sc.nextInt();
            stack.push(item);
            queue.enqueue(String.valueOf(item));
            bag.add(new Clazz(item));
        }

        System.out.println("Stack [" + stack.size() + "]:");
        Integer x = stack.pop();
        System.out.println("--> poping: " + x + "; size: " + stack.size());
        for (Integer item : stack) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("Queue [" + queue.size() + "]:");
        String y = queue.dequeue();
        System.out.println("--> dequeue: " + y + "; size: " + queue.size());
        for (String item : queue) {
            System.out.println(item);
        }

        System.out.println();

        System.out.println("Bag [" + bag.size() + "]:");
        for (Clazz item : bag) {
            System.out.println(item);
        }
        ///////////////////////////////////////////////////////////////////////
    }
}
