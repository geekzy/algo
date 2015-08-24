package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * BFS implementation of Graph traversing algorithm.
 *
 * @author Ade Imam Kurniawan (ade.imam@samsung.com) SRIN
 */
@SuppressWarnings("unused")
public class GraphBfsAlgorithm {

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
     * using Stack algorithm to store data.
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
    @SuppressWarnings("unchecked")
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
         * Adjecency List of the Graph
         */
        Bag<Integer>[] adjList;

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

        public Bag<Integer> adjList(int v) {
            // adjecency list of v
            return adjList[v];
        }
    }

    private class BreadthFirstSearch {
        // visted marker
        boolean[] marked;
        // last vertext on known path
        int[] edgeTo;
        // visited count
        int count;
        // source
        int source;

        public BreadthFirstSearch(Graph g, Integer s) {
            // instantiate the marker
            marked = new boolean[g.V];
            // instantiate last known path
            edgeTo = new int[g.V];
            // assign the source
            this.source = s;
            // start traversing
            bfs(g, s);
        }

        public boolean hasPathTo(Integer s) {
            return marked[s];
        }

        public Bag<Integer> pathTo(Integer s) {
            return null;
        }

        private void bfs(Graph g, Integer s) {
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        GraphBfsAlgorithm app = new GraphBfsAlgorithm();
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

            BreadthFirstSearch search = new BreadthFirstSearch(graph, S);
            for (int v = 0; v < graph.V; v++) {
                System.out.print(S + " to " + v + ": ");
                if (search.hasPathTo(v)) {
                    Bag<Integer> paths = search.pathTo(v);
                    for (Node<Integer> node = paths.first; node != null; node = node.next) {
                        Integer i = node.item;
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
