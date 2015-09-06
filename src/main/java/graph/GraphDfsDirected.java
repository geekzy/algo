package graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings({"unchecked", "unused"})
public class GraphDfsDirected {

    class Node<T> {
        T item;
        Node<T> next;
    }

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
            Node<T> f = first;
            first = new Node<>();
            first.item = item;
            first.next = f;
            N++;
        }
    }

    class Graph {
        final int V;
        int E;
        Bag<Integer>[] adjList;

        public Graph(int V) {
            this.V = V;
            adjList = (Bag<Integer>[]) new Bag[V];
            for (int v = 0; v < V; v++) adjList[v] = new Bag<>();
        }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
            E++;
        }

        public Graph reverse() {
            Graph g = new Graph(V);
            for (int v = 0; v < V; v++) {
                Bag<Integer> adj = adjList[v];
                for (Node<Integer> n = adj.first; n != null; n = n.next)
                    g.addEdge(n.item, v);
            }
            return g;
        }
    }

    class DepthFirstSearch {
        boolean[] marked;

        public DepthFirstSearch(int V) {
            marked = new boolean[V];
        }

        public DepthFirstSearch(Graph g, int s) {
            this(g.V);
            dfs(g, s);
        }

        public DepthFirstSearch(Graph g, Bag<Integer> sources) {
            this(g.V);
            for (Node<Integer> n = sources.first; n != null; n = n.next)
                if (!marked[n.item]) dfs(g, n.item);
        }

        private void dfs(Graph g, int s) {
            marked[s] = true;
            Bag<Integer> adj = g.adjList[s];
            for (Node<Integer> n = adj.first; n != null; n = n.next)
                if (!marked[n.item]) dfs(g, n.item);
        }
    }

    private void start() throws Throwable {
        System.setIn(new FileInputStream("probs/graph/tinyDG.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int S = sc.nextInt();

            Graph graph = new Graph(V);
            for (int e = 0; e < E; e++) {
                int v = sc.nextInt();
                int w = sc.nextInt();

                graph.addEdge(v, w);
            }

            Bag<Integer> sources = new Bag<>();
            for (int s = 0; s < S; s++) sources.add(sc.nextInt());

            DepthFirstSearch dfs = new DepthFirstSearch(graph, sources);
            for (int v = 0; v < graph.V; v++)
                if (dfs.marked[v]) System.out.print(v + " ");
            System.out.println();
        }

        sc.close();
    }

    public static void main(String[] args) throws Throwable {
        GraphDfsDirected app = new GraphDfsDirected();
        app.start();
    }
}
