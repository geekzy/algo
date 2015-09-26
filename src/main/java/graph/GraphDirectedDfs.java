package graph;

import java.io.FileInputStream;
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
        int s;

        public DepthFirstPath(Graph g, int s) {
            this.s = s;
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
            for (int x = v; x != s; x = edge[x]) {
                pathTo.push(x);
            }
            pathTo.push(s);
            return pathTo;
        }
    }

    class Cycle {
        boolean[] marked;
        boolean[] onStack;
        int[] edgeTo;
        Stack cycles;

        public Cycle(Graph g) {
            marked = new boolean[g.V];
            onStack = new boolean[g.V];
            edgeTo = new int[g.V];

            for (int v = 0; v < g.V; v++) {
                if (!marked[v] && cycles == null) dfs(g, v);
            }
        }

        public void dfs(Graph g, int v) {
            marked[v] = true;
            onStack[v] = true;
            for (Node n = g.adjList[v].first; n != null; n = n.next) {
                int w = n.item;
                if (cycles != null) return;
                else if (!marked[v]) {
                    edgeTo[w] = v;
                    dfs(g, w);
                }
                else if (onStack[w]) {
                    // record cycle into stack
                    cycles = new Stack();
                    for (int x = v; x != w; x = edgeTo[x])
                        cycles.push(x);
                    cycles.push(w);
                    cycles.push(v);
                }
            }
        }
    }

    void start() throws Throwable {
        System.setIn(new FileInputStream("probs/graph/tinyDG.txt"));
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int E = sc.nextInt();

        long start = System.currentTimeMillis();

        Graph graph = new Graph(V);
        for (int e = 0; e < E; e++) {
            int v = sc.nextInt();
            int w = sc.nextInt();

            graph.addEdge(v, w);
        }

        int S = 6;
        int T = 1;
        DepthFirstPath dfs = new DepthFirstPath(graph, S);
        Stack pathTo = dfs.pathTo(T);

        if (pathTo != null) {
            System.out.println("Path to " + S + " are:");
            while (!pathTo.empty()) System.out.print(pathTo.pop() + " ");
            System.out.println();
        }
        else System.out.println(String.format("No path from %d to %d", S, T));

        long end = System.currentTimeMillis();
        System.out.println();
        System.out.println("Running in " + (end - start) + "ms");
    }

    public static void main(String[] args) throws Throwable {
        GraphDirectedDfs app = new GraphDirectedDfs();
        app.start();
    }
}
