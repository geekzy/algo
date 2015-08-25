package graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * DFS implementation of Graph traversing algorithm.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings({"unused", "unchecked"})
public class GraphDfsAlgorithm {

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

    /**
     * Depth first search algorithm to traverse through a Graph
     */
    private class DepthFirstSearch {
        // visted marker
        boolean[] marked;
        // last vertext on known path
        int[] edgeTo;
        // visited count
        int count;
        // source
        int source;

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

        public Bag<Integer> pathTo(int v) {
            // if v was never visted then obviously no path
            if (!hasPathTo(v)) return null;
            // prepare the collection of paths
            Bag<Integer> path = new Bag<>();
            // traverse from v the way to source using edgeTo
            for (int x = v; x != source; x = edgeTo[x]) path.add(x);
            path.add(source); // finally push source to make it first node in collection
            // return the Stack
            return path;
        }

        public void dfs(Graph g, int v) {
            // mark v as visted
            marked[v] = true;
            // traversing counter increment
            count++;
            // start looking for v's adjecency items
            Bag<Integer> adjList = g.adjList(v);
            for (Node<Integer> n = adjList.first; n != null; n = n.next) {
                Integer w = n.item;
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

    public static void main(String[] args) throws Throwable {
        GraphDfsAlgorithm app = new GraphDfsAlgorithm();
        app.start();
    }

    private void start() throws Throwable {
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

            DepthFirstSearch path = new DepthFirstSearch(graph, S);
            for (int v = 0; v < graph.V; v++) {
                System.out.print(S + " to " + v + ": ");
                if (path.hasPathTo(v)) {
                    Bag<Integer> Bag = path.pathTo(v);
                    for (Node<Integer> n = Bag.first; n != null; n = n.next) {
                        Integer i = n.item;
                        if (i == S) System.out.print(i);
                        else System.out.print("-" + i);
                    }
                }
                System.out.println();
            }

            if (path.count != graph.V) System.out.print("NOT ");
            System.out.println("Connected!");
            System.out.println();
            ///////////////////////////////////////////////////////////////////
        }
    }
}
