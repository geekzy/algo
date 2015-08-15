package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * DFS implementation of Graph traversing algorithm.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings("unused")
public class GraphDfsAlgorithm {

    /**
     * Simple Node with generic Type.
     *
     * @param <T> The type of the Node.
     */
    private class Node<T> {
        /**
         * The type of the node.
         */
        T item;
        /**
         * The weight of the node.
         */
        int weight;
        /**
         * Next node connected.
         */
        Node<T> next;
    }

    /**
     * Simple data structure iterator, iterating relatively from first node.
     *
     * @param <T> The type of the node to iterate.
     */
    private class StrucIterator<T> implements Iterator<T> {
        /**
         * Current node being iterated.
         */
        private Node<T> current;

        public StrucIterator(Node<T> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            // grab the item
            T item = current.item;
            // iterate to next node
            current = current.next;
            // return the item
            return item;
        }
    }

    /**
     * Basic data structure to store collection of items with type T
     *
     * @param <T> The type of the item to be stored.
     */
    private class Bag<T> implements Iterable<T> {
        Node<T> first;
        int N;

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        public void add(T item) {
            Node<T> old = first;
            first = new Node<>();
            first.item = item;
            first.next = old;
            N++;
        }

        @Override
        public Iterator<T> iterator() {
            return new StrucIterator<>(first);
        }
    }

    /**
     * Basic stack bahaviour data structure.
     *
     * @param <T> The type of the stack.
     */
    private class Stack<T> implements Iterable<T> {
        private Node<T> first;
        private int N;

        public boolean isEmpty() {
            return first == null;
        }

        public int count() {
            return N;
        }

        public void push(T item) {
            Node<T> old = first;
            first = new Node<>();
            first.item = item;
            first.next = old;
            N++;
        }

        public T pop() {
            T item = first.item;
            first = first.next;
            N--;
            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new StrucIterator<>(first);
        }
    }

    /**
     * Graph represention
     */
    @SuppressWarnings("unchecked")
    private class Graph {
        /**
         * Vertex of the Graph
         */
        private final int V;
        /**
         * Edge count of the Graph
         */
        private int E;
        /**
         * Adjecency List of the Graph
         */
        private Bag<Integer>[] adjList;

        public Graph(int V) {
            // holds total of vertex and initialize edge count
            this.V = V;
            this.E = 0;
            // create array of adjecency list
            adjList = (Bag<Integer>[]) new Bag[V];
            // instantiate each adjecency list in array
            for (int v = 0; v < V; v++) adjList[v] = new Bag<>();
        }

        public void addEdge(int v, int w) {
            // add w to v's adjecency list
            adjList[v].add(w);
            // add v to w's adjecency list (since it's undirected graph)
            adjList[w].add(v);
            // edge count increment
            E++;
        }

        Iterable<Integer> adjList(int v) {
            // iterable instace of adjecency list
            return adjList[v];
        }
    }

    /**
     * Depth first search algorithm to traverse through a Graph
     */
    private class DepthFirstSearch {
        // visted marker
        private boolean[] marked;
        // last vertext on known path
        private int[] edgeTo;
        // visited count
        private int count;
        // source
        private int source;

        public DepthFirstSearch(Graph g, int source) {
            // instantiate the marker
            marked = new boolean[g.V];
            // instantiate last knwon path
            edgeTo = new int[g.V];
            // assign source
            this.source = source;
            // start traversing
            dfs(g, source);
        }

        public boolean hasPathTo(int s) {
            // marker of s, true for visited, otherwise false
            return marked[s];
        }

        public int count() {
            return count;
        }

        public Iterable<Integer> pathTo(int v) {
            // if no path to v then obviously no path
            if (!hasPathTo(v)) return null;
            // put in stack as it's called depth first search
            Stack<Integer> path = new Stack<>();
            // traverse from v the way to source
            for (int x = v; x != source; x = edgeTo[x]) path.push(x);
            path.push(source); // finally push source to make it first node in stack
            // return the stack
            return path;
        }

        private void dfs(Graph g, int v) {
            // mark v as visted
            marked[v] = true;
            // traversing counter increment
            count++;
            // start looking for v's adjecency items
            for (Integer w : g.adjList(v)) {
                // if hasn't been visited, start traversing from there
                if (!marked[w]) {
                    // mark last known path to v
                    edgeTo[w] = v;
                    // recursively search from w
                    dfs(g, w);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        GraphDfsAlgorithm app = new GraphDfsAlgorithm();
        app.start();
    }

    private void start() throws FileNotFoundException {
        System.setIn(new FileInputStream("probs/graph/simple_1.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {
            ///////////////////////////////////////////////////////////////////

            int V = sc.nextInt();
            int E = sc.nextInt();
            int S = sc.nextInt();

            Graph graph = new Graph(V);
            for (int e = 0; e < E; e++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                graph.addEdge(a, b);
            }

            DepthFirstSearch search = new DepthFirstSearch(graph, S);
            for (int v = 0; v < graph.V; v++) {
                System.out.print(S + " to " + v + ": ");
                if (search.hasPathTo(v)) {
                    for (Integer i : search.pathTo(v)) {
                        if (i == S) System.out.print(i);
                        else System.out.print("-" + i);
                    }
                }
                System.out.println();
            }

            if (search.count != graph.V) System.out.print("NOT ");
            System.out.println("Connected!");
            System.out.println();
            ///////////////////////////////////////////////////////////////////
        }
    }
}
