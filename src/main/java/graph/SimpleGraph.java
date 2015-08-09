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

    private class Bag<T> implements Iterable<T> {
        Node first;
        int N;

        public boolean isEmpty() {
            return first == null;
        }

        public int size() {
            return N;
        }

        public void add(T item) {
            Node node = new Node();
            node.item = item;
            node.next = first;
            first = node;
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

    public class Graph {
        final int V;
        int E;
        Bag<Integer>[] adjList;

        @SuppressWarnings("unchecked")
        public Graph(int V) {
            this.V = V;
            adjList = (Bag<Integer>[]) new Bag[this.V];
            // initialize adjecency list
            for (int v = 0; v < this.V; v++) {
                adjList[v] = new Bag<>();
            }
        }

        public void addEdge(int v, int w) {
            // undirected so add both
            adjList[v].add(w);
            adjList[w].add(v);
            E++;
        }

        public Iterable<Integer> adjList(int v) {
            return adjList[v];
        }
    }

    private class DepthFirstSearch {
        boolean[] marked;
        int count;

        public DepthFirstSearch(Graph G, int s) {
            marked = new boolean[G.V];
            dfs(G, s);
        }

        public void dfs(Graph G, int v) {
            marked[v] = true;
            count++;
            for (Integer w : G.adjList(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        SimpleGraph app = new SimpleGraph();
        app.start();
    }

    private void start() throws FileNotFoundException {
        System.setIn(new FileInputStream("probs/graph/simple_1.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for (int tc = 0; tc < T; tc++) {

            int V = sc.nextInt();
            int E = sc.nextInt();

            Graph G = new Graph(V);
            for (int e = 0; e < E; e++) {

                int v = sc.nextInt();
                int w = sc.nextInt();

                G.addEdge(v, w);
            }

            DepthFirstSearch search = new DepthFirstSearch(G, 0);
            for (int v = 0; v < G.V; v++) {
                if (search.marked[v]) System.out.print(v + " ");
            }/*
            for (int v = 0; v < V; v++) {
                String items = "";
                System.out.print("Adjecency List for " + v + ": ");
                for (Integer item : G.adjList(v)) {
                    items += "-" + item;
                }
                System.out.println(items.substring(1));
            }*/
            System.out.println();
            if (search.count != G.V) System.out.print("NOT ");
            System.out.println("Connected!");

            System.out.println("--------");
        }
    }
}
