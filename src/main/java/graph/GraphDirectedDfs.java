package graph;

import java.io.FileInputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Directed Graph DFS implementation
 *
 * @author Ade Imam Kurniawan (ade.imam@samsung.com) SRIN
 */
public class GraphDirectedDfs {
    class Node {
        int item;
        Node next;
    }

    class Bag {
        Node first;
        int N;

        public boolean empty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        public void add(int item) {
            Node f = first;
            first = new Node();
            first.item = item;
            first.next = f;
            N++;
        }
    }

    class Stack extends Bag {
        public void push(int item) {
            add(item);
        }

        public int pop() {
            int item = first.item;
            first = first.next;
            N--;
            return item;
        }
    }

    class Graph {
        int V;
        int E;
        Bag[] adjList;

        public Graph(int V) {
            this.V = V;
            adjList = new Bag[V];
            for (int v = 0; v < V; v++) {
                adjList[v] = new Bag();
            }
        }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
            E++;
        }
    }

    class DepthFirstPath {
        boolean[] marked;
        int[] edge;

        public DepthFirstPath(Graph g, int s) {
            marked = new boolean[g.V];
            edge = new int[g.V];

            dfs(g, s);
        }

        public void dfs(Graph g, int v) {
            marked[v] = true;
            for (Node n = g.adjList[v].first; n != null; n = n.next) {
                int w = n.item;
                if (!marked[w]) {
                    edge[w] = v;
                    dfs(g, w);
                }
            }
        }

        public Stack pathTo(int v) {
            if (!marked[v]) return null;
            Stack pathTo = new Stack();
            for (int x = edge[v]; x != v; x = edge[x]) {
                pathTo.push(x);
            }
            pathTo.push(v);
            return pathTo;
        }
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/graph/tinyDG.txt"));
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        Graph graph = new Graph(V);
        for (int e = 0; e < E; e++) {
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph.addEdge(v, w);
        }

        int S = new Random().nextInt(V);
        DepthFirstPath dfs = new DepthFirstPath(graph, 0);
        Stack pathTo = dfs.pathTo(S);

        System.out.println("Path to " + S + " are:");
        while (!pathTo.empty()) System.out.print(pathTo.pop() + " ");
        System.out.println();
    }

    public static void main(String[] args) throws Throwable {
        GraphDirectedDfs app = new GraphDirectedDfs();
        app.start();
    }
}
