package graph;

import java.io.FileInputStream;
import java.util.Scanner;

/**
 * BFS implementation of Graph traversing algorithm.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings({"unused", "unchecked"})
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
     * using Stack implementation to store items
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
     * Queue data structure to support BFS when traversing Graph
     */
    private class Queue<T> {
        Node<T> first;
        Node<T> last;
        int N;

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        public void enqueue(T item) {
            Node<T> l = last;
            last = new Node<>();
            last.item = item;
            if (isEmpty()) first = last;
            else last.next = l;
            N++;
        }

        public T dequeue() {
            T item = first.item;
            first = first.next;
            if (isEmpty()) last = null;
            N--;
            return item;
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
     * Breadth first search algorithm to traverse through a Graph
     */
    private class BreadthFirstPath {
        // visited marker
        boolean[] marked;
        // last vertex on known path
        int[] edgeTo;
        // source
        int source;
        // visited count
        int count;

        public BreadthFirstPath(Graph g, int s) {
            // instantiate marker
            marked = new boolean[g.V];
            // instantiate last vertext on known path
            edgeTo = new int[g.V];
            // assign source
            this.source = s;
            // start traversing
            bfs(g, s);
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public Bag<Integer> pathTo(int v) {
            // if v was never visted then obviously no path
            if (!hasPathTo(v)) return null;
            // prepare the collection of paths
            Bag<Integer> path = new Bag<>();
            // traverse from v the way to source using edgeTo
            for (int x = v; x != source; x = edgeTo[x]) path.add(x);
            path.add(source);
            return path;
        }

        private void bfs(Graph g, int s) {
            // create a queue
            Queue<Integer> queue = new Queue<>();
            // mark the source as visited
            marked[s] = true;
            count++;
            // put it on the queue
            queue.enqueue(s);
            while(!queue.isEmpty()) {
                // remove next vertex from the queue
                Integer v = queue.dequeue();
                // get the adjecency of the vertex
                Bag<Integer> adjList = g.adjList[v];
                for (Node<Integer> n = adjList.first; n != null; n = n.next) {
                    Integer w = n.item;
                    // for every unmarked adjecency vertex
                    if (!marked[w]) {
                        // save last edge on a shortest path
                        edgeTo[w] = v;
                        // mark it cos it's now known
                        marked[w] = true;
                        count++;
                        // add it to the queue
                        queue.enqueue(w);
                    }
                }
            }
        }
    }

    public void start() throws Throwable {
        System.setIn(new FileInputStream("probs/graph/simple_1.txt"));
        Scanner sc = new Scanner(System.in);

        ///////////////////////////////////////////////////////////////////////
        //                          Algorithm Test                           //
        ///////////////////////////////////////////////////////////////////////
        int TC = sc.nextInt();
        for (int tc = 0; tc < TC; tc++) {

            int V = sc.nextInt();
            int E = sc.nextInt();
            int S = sc.nextInt();

            Graph graph = new Graph(V);
            for (int e = 0; e < E; e++) {
                int a = sc.nextInt();
                int b = sc.nextInt();

                graph.addEdge(a, b);
            }

            BreadthFirstPath path = new BreadthFirstPath(graph, S);
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
        }
        ///////////////////////////////////////////////////////////////////////
    }

    public static void main(String[] args) throws Throwable {
        GraphBfsAlgorithm app = new GraphBfsAlgorithm();
        app.start();
    }
}