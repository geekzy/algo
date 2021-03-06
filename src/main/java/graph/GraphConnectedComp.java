package graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * Cycle checking implementation of Graph algorithm.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings({"unused", "unchecked"})
public class GraphConnectedComp {

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
     * Connected components detection in a {@link Graph}.
     */
    private class ConnectedComp {
        boolean[] marked;
        int[] id;
        int count;

        public ConnectedComp(Graph g) {
            marked = new boolean[g.V];
            id = new int[g.V];

            for (int s = 0; s < g.V; s++)
                if (!marked[s]) {
                    dfs(g, s);
                    count++;
                }
        }

        private void dfs(Graph g, int v) {
            marked[v] = true;
            id[v] = count;
            for (Node<Integer> n = g.adjList[v].first; n != null; n = n.next) {
                Integer w = n.item;
                if (!marked[w]) dfs(g, w);
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        GraphConnectedComp app = new GraphConnectedComp();
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

            Graph graph = new Graph(V);
            for (int e = 0; e < E; e++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                graph.addEdge(a, b);
            }

            ConnectedComp cc = new ConnectedComp(graph);
            int M = cc.count;
            System.out.println("#" + tc + " connected components: " + M);

            Bag<Integer>[] comps = (Bag<Integer>[]) new Bag[M];
            for (int m = 0; m < M; m++) comps[m] = new Bag<>();
            for (int v = 0; v < graph.V; v++) comps[cc.id[v]].add(v);

            for (int m = 0; m < M; m++) {
                System.out.print("\t> " + (m + 1) + " ");
                for (Node<Integer> n = comps[m].first; n != null; n = n.next)
                    System.out.print(n.item + " ");
                System.out.println();
            }

        }
        long totalEnd = System.currentTimeMillis();
        System.out.println();
        System.out.print("Total : " + (totalEnd - totalStart) + "ms");
        ///////////////////////////////////////////////////////////////////////
        sc.close();
    }
}
