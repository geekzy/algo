package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementations of basic data structures.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings("unused")
public class BasicStucture {
    /**
     * Simple Node with generic Type.
     *
     * @param <T> The type of the Node.
     */
    class Node<T> {
        /**
         * The value of the type of the node.
         */
        T item;
        /**
         * The weight of the node.
         */
        int weight;
        /**
         * Next linked node.
         */
        Node<T> next;
    }

    /**
     * Stack data structure implementation.
     *
     * @param <T> The type of a single node in the stack.
     */
    class Stack<T> {
        /**
         * Top node of the stack
         */
        Node<T> first;
        /**
         * Total nodes in the stack
         */
        int N;

        public boolean isEmpty() {
            return first == null;
        }
        public int size() { return N; }

        public void push(T item) {
            Node<T> n = first;
            first = new Node<>();
            first.item = item;
            first.next = n;
            N++;
        }

        public T pop() {
            T item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    /**
     * Queue data structure implementation.
     *
     * @param <T> The type of a single node in the queue.
     */
    class Queue<T> {
        /**
         * First node of the queue.
         */
        Node<T> first;
        /**
         * Last node of the queue.
         */
        Node<T> last;
        /**
         * Total nodes in queue.
         */
        int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void enqueue(T item) {
            Node<T> n = last;
            last = new Node<>();
            last.item = item;
            if (isEmpty()) first = last;
            else n.next = last;
            N++;
        }

        public T dequeue() {
            T item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
        }
    }

    /**
     * Basic collection data structure, implemeted with stack algorithm.
     *
     * @param <T> The type of a single node in the bag.
     */
    class Bag<T> {
        /**
         * First node of the bag.
         */
        Node<T> first;
        /**
         * Total nodes in the bag.
         */
        int N;

        public boolean isEmpty() { return first == null; }
        public int size() { return N; }

        public void add(T item) {
            Node<T> n = first;
            first = new Node<>();
            first.item = item;
            first.next = n;
            N++;
        }

        public T get() {
            T item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    class Clazz {
        final Integer item;

        public Clazz(Integer item) {
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

    void start() throws FileNotFoundException {
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
        for (Node<Integer> n = stack.first; n != null; n = n.next) {
            System.out.println(n.item);
        }

        System.out.println();

        System.out.println("Queue [" + queue.size() + "]:");
        String y = queue.dequeue();
        System.out.println("--> dequeue: " + y + "; size: " + queue.size());
        for (Node<String> n = queue.first; n != null; n = n.next) {
            System.out.println(n.item);
        }

        System.out.println();

        System.out.println("Bag [" + bag.size() + "]:");
        for (Node<Clazz> n = bag.first; n != null; n = n.next) {
            System.out.println(n.item);
        }
        ///////////////////////////////////////////////////////////////////////
    }
}
