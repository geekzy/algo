package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Implementations of basic data structures.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings({"unchecked"})
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

        public int weightOf(T item) {
            for (Node<T> n = first; n != null; n = n.next)
                if (n.item == item) return n.weight;
            return 0;
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
            enqueue(item, 0);
        }

        public void enqueue(T item, int weight) {
            // backup first node
            Node<T> n = last;
            // create new node
            last = new Node<>();
            // assign values
            last.item = item;
            last.weight = weight;
            // set next or first should equals last
            if (isEmpty()) first = last;
            else n.next = last;
            // increase counter
            N++;
        }

        public T dequeue() {
            // extract item
            T item = first.item;
            // set to next pointer
            first = first.next;
            // reset last
            if (isEmpty()) last = null;
            // decrease counter
            N--;
            return item;
        }

        public int weightOf(T item) {
            for (Node<T> n = first; n != null; n = n.next)
                if (n.item == item) return n.weight;
            return 0;
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
            add(item, 0); // add with default of 0 weight
        }

        public void add(T item, int weight) {
            // backup first node
            Node<T> n = first;
            // create new node
            first = new Node<>();
            // assign values
            first.item = item;
            first.weight = weight;
            first.next = n;
            // increase counter
            N++;
        }

        public int weightOf(T item) {
            for (Node<T> n = first; n != null; n = n.next)
                if (n.item == item) return n.weight;
            return 0;
        }
    }

    /**
     * Graph represention
     */
    class Graph {
        /**
         * Vertex of the Graph
         */
        final int V;
        /**
         * Edge count of the Graph
         */
        int E;
        /**
         * Indicator of wheather nodes are directed.
         */
        final boolean digraph;
        /**
         * Adjecency List of the Graph
         */
        final Bag<Integer>[] adjList;

        public Graph(int V) {
            this(V, false);
        }

        public Graph(int V, boolean digraph) {
            // holds total of vertex and initialize edge count
            this.V = V;
            this.E = 0;
            this.digraph = digraph;
            // create array of adjecency list
            adjList = (Bag<Integer>[]) new Bag[V];
            // instantiate each adjecency list in array
            for (int v = 0; v < V; v++) adjList[v] = new Bag<>();
        }

        public void addEdge(int v, int w) {
            // add w to v's adjecency list
            adjList[v].add(w);
            // add v to w's adjecency list (for undirected graph)
            if (!digraph) adjList[w].add(v);
            // edge count increment
            E++;
        }

        public Graph reverse() {
            // can only be applied to directed graph
            if (!digraph) return null;
            // new copy of a graph with the same total of vertices
            Graph g = new Graph(V);
            // loop through all vertices and its adjecencies
            for (int v = 0; v < V; v++) {
                for (Node<Integer> n = adjList[v].first; n != null; n = n.next)
                    // add edge with reversing order to new graph
                    g.addEdge(n.item, v);
            }
            return g;
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
        sc.close();
    }
}
