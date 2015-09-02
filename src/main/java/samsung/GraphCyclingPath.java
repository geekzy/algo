package samsung;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
class GraphCyclingPath {

    class Node {
        int item;
        Node next;
    }

    class Bag {
        Node first;
        int N;

        boolean empty() {
            return first == null;
        }

        int size() {
            return N;
        }

        void add(int item) {
            Node f = first;
            first = new Node();
            first.item = item;
            first.next = f;
            N++;
        }
    }

    class Graph {
        final int V;
        final boolean directed;
        int E;
        Bag[] adjList;

        Graph(int V) {
            this(V, false);
        }

        Graph(int V, boolean directed) {
            this.V = V;
            this.directed = directed;
            adjList = new Bag[V];
            for (int v = 0; v < V; v++) adjList[v] = new Bag();
        }

        void addEdge(int v, int w) {
            adjList[v].add(w);
            if (!directed) adjList[w].add(v);
            E++;
        }
    }

    class Cycle {
        boolean[] marked;
        boolean hasCycle;
        Bag cycles = new Bag();

        Cycle(Graph g) {
            marked = new boolean[g.V];
            for (int s = 0; s < g.V; s++)
                if (!marked[s]) dfs(g, s, s);
        }

        void dfs(Graph g, int s, int t) {
            marked[s] = true;
            Bag adjList = g.adjList[s];
            for (Node n = adjList.first; n != null; n = n.next) {
                int w = n.item;
                if (!marked[w]) dfs(g, w, t);
                else if (s != t) hasCycle = true;
            }
        }
    }

    public static void main(String[] args) throws Throwable {
        GraphCyclingPath app = new GraphCyclingPath();
        app.start();
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/Problem_20150722/sample_input_1.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        for (int tc = 1; tc <= TC; tc++) {
            int V = sc.nextInt();
            int E = sc.nextInt();

            Graph graph = new Graph(V, true);
            for (int e = 0; e < E; e++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                graph.addEdge(v, w);
            }
        }
    }
}
