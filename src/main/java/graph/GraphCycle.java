package graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Cycle checking implementation of Graph algorithm.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings({"unused", "unchecked"})
public class GraphCycle {

    /**
     * Simple Node with generic Type.
     *
     * @param <T> The type of the Node.
     */
    class Node<T> {
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
     * Basic data structure to store collection of items with type T
     *
     * @param <T> The type of the item to be stored.
     */
    class Bag<T> {
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
        final boolean directed;
        /**
         * Adjecency List of the Graph
         */
        final Bag<Integer>[] adjList;

        public Graph(int V) {
            this(V, false);
        }

        public Graph(int V, boolean directed) {
            // holds total of vertex and initialize edge count
            this.V = V;
            this.E = 0;
            this.directed = directed;
            // create array of adjecency list
            adjList = (Bag<Integer>[]) new Bag[V];
            // instantiate each adjecency list in array
            for (int v = 0; v < V; v++) adjList[v] = new Bag<>();
        }

        public void addEdge(int v, int w) {
            // add w to v's adjecency list
            adjList[v].add(w);
            // add v to w's adjecency list (for undirected graph)
            if (!directed) adjList[w].add(v);
            // edge count increment
            E++;
        }
    }

    /**
     * Cycle detection in a {@link Graph}.
     */
    class Cycle {
        boolean[] marked;
        boolean hasCycle;

        public Cycle(Graph g) {
            marked = new boolean[g.V];
            for (int s = 0; s < g.V; s++) if (!marked[s]) dfs(g, s, s);
        }

        private void dfs(Graph g, int s, int t) {
            marked[s] = true;
            for (Node<Integer> n = g.adjList[s].first; n != null; n = n.next) {
                Integer v = n.item;
                if (!marked[v]) dfs(g, v, s);
                else if (s != t) {
                    hasCycle = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        GraphCycle app = new GraphCycle();
        app.start();
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/graph/simple_1.txt"));
        Scanner sc = new Scanner(System.in);

        ///////////////////////////////////////////////////////////////////////
        //                          Algorithm Test                           //
        ///////////////////////////////////////////////////////////////////////
        int T = sc.nextInt();
        long totalStart = System.currentTimeMillis();
        for (int tc = 1; tc <= T; tc++) {
            long start = System.currentTimeMillis();

            int V = sc.nextInt();
            int E = sc.nextInt();
            int S = sc.nextInt();

            Graph graph = new Graph(V, true);
            for (int e = 0; e < E; e++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                graph.addEdge(a, b);
            }

            Cycle cycle = new Cycle(graph);
            System.out.print("#" + tc + " " + (cycle.hasCycle ? "Has" : "No") + " Cycle(s)");
            long end = System.currentTimeMillis();
            System.out.println(" => " + (end - start ) + "ms");
        }
        long totalEnd = System.currentTimeMillis();
        System.out.println();
        System.out.print("Total : " + (totalEnd - totalStart) + "ms");
        ///////////////////////////////////////////////////////////////////////
    }
}
