package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

        public DepthFirstSearch(Graph g, Integer source) {
            // instantiate the marker
            marked = new boolean[g.V];
            // instantiate last known path
            edgeTo = new int[g.V];
            // assign source
            this.source = source;
            // start traversing
            dfs(g, source);
        }

        public boolean hasPathTo(Integer s) {
            // marker of s, true for visited, otherwise false
            return marked[s];
        }

        public Bag<Integer> pathTo(Integer v) {
            // if no path to v then obviously no path
            if (!hasPathTo(v)) return null;
            // put in stack as it's called depth first search
            Bag<Integer> path = new Bag<>();
            // traverse from v the way to source
            for (int x = v; x != source; x = edgeTo[x]) path.add(x);
            path.add(source); // finally push source to make it first node in stack
            // return the stack
            return path;
        }

        private void dfs(Graph g, Integer v) {
            // mark v as visted
            marked[v] = true;
            // traversing counter increment
            count++;
            // start looking for v's adjecency items
            Bag<Integer> bag = g.adjList(v);
            for (Node<Integer> node = bag.first; node != null; node = node.next) {
                Integer w = node.item;
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
