package graph;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
@SuppressWarnings("unused")
public class SimpleGraph {

    private class Stack<T> implements Iterable<T> {
        Node first;
        int N;

        public boolean isEmpty() {
            return first == null;
        } // or N == 0;

        public int size() {
            return N;
        }

        public void push(T item) {
            Node old = first;
            first = new Node();
            first.item = item;
            first.next = old;
            N++;
        }

        public T pop() {
            T item = first.item;
            first = first.next;
            N--;
            return item;
        }

        @Override
        public Iterator<T> iterator() {
            return new StackIterator();
        }

        private class StackIterator implements Iterator<T> {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        }

        private class Node {
            T item;
            Node next;
        }
    }

    private class Bag<T> implements Iterable<T> {
        Node first;
        int N;

        public boolean isEmpty() {
            return first == null;
        } // or N == 0;

        public int size() {
            return N;
        }

        public void add(T item) {
            Node old = first;
            first = new Node();
            first.item = item;
            first.next = old;
            N++;
        }

        @Override
        public Iterator<T> iterator() {
            return new BagIterator();
        }

        private class BagIterator implements Iterator<T> {
            Node current = first;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                T item = current.item;
                current = current.next;
                return item;
            }
        }

        private class Node {
            T item;
            Node next;
        }
    }

    private class Graph {
        // number of vertices
        final int V;
        // number of edges
        int E;
        // ajacency list
        Bag<Integer>[] adjList;

        public Graph(int V) {
            this.V = V;
            this.E = 0;
            // create array of list (bag)
            adjList = (Bag<Integer>[]) new Bag[V];
            // initialize all item to empty
            for (int v = 0; v < V; v++)
                adjList[v] = new Bag<Integer>();
        }

        public void addEdge(int v, int w) {
            adjList[v].add(w);
            adjList[w].add(v);
            E++;
        }

        public Iterable<Integer> adjList(int v) {
            return adjList[v];
        }
    }

    private interface Path<T> {
        // is there a path from s to v?
        boolean hasPathTo(T v);

        // Path from s to v
        Iterable<T> pathTo(T v);
    }

    private class DepthFirstPath implements Path<Integer> {
        // source/start of the trace
        final int s;
        // Has dfs been called for this vertex
        boolean[] marked;
        // last vertex on known path to this vertex
        int[] edgeTo;

        private DepthFirstPath(Graph g, int s) {
            this.s = s;
            marked = new boolean[g.V];
            edgeTo = new int[g.V];
            dfs(g, s);
        }

        private void dfs(Graph g, int v) {
            marked[v] = true;
            for (Integer w : g.adjList(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(g, w);
                }
            }
        }

        public boolean hasPathTo(Integer v) {
            return marked[v];
        }

        public Iterable<Integer> pathTo(Integer v) {
            if (!hasPathTo(v)) return null;
            Stack<Integer> path = new Stack<Integer>();
            for (int x = 0; x != s; x = edgeTo[x]) {
                path.push(x);
            }
            path.push(s);
            return path;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        SimpleGraph app = new SimpleGraph();
        app.start();
    }

    private void start() throws FileNotFoundException {
        System.setIn(new FileInputStream("probs/graph/simple_1.txt"));
        Scanner sc = new Scanner(System.in);

        for (int t = 0; t < 2; t++) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int S = sc.nextInt();

            Graph g = new Graph(V);
            /////////////////////////////////////////////////////////////
            for (int e = 0; e < E; e++) {
                int v = sc.nextInt();
                int w = sc.nextInt();
                g.addEdge(v, w);
            }
            System.out.println("Graph " + (t + 1) + " Loaded!");

            Path<Integer> search = new DepthFirstPath(g, S);
            for (int v = 0; v < g.V; v++) {
                System.out.print(S + " to " + v + ": ");
                if (search.hasPathTo(v)) {
                    for (Integer x : search.pathTo(v)) {
                        if (x == S) System.out.print(x);
                        else System.out.print("-" + x);
                    }
                }
                System.out.println();
            }
            /////////////////////////////////////////////////////////////

        }
    }
}
