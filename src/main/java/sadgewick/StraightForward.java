package sadgewick;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Ade Imam Kurniawan (ade.imam@samsung.com) SRIN
 */
public class StraightForward {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new FileInputStream("probs/sadgewick/array_struct_1.txt"));
        //Scanner sc = new Scanner(System.in);

        ArrayStack<Integer> stack = new ArrayStack<>();
        int T = sc.nextInt();
        while(T > 0) {
            int item = sc.nextInt();
            stack.push(item);
            T--;
        }

        sc = new Scanner(new FileInputStream("probs/sadgewick/array_struct_1.txt"));
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        T = sc.nextInt();
        while(T > 0) {
            int item = sc.nextInt();
            queue.enqueue(item);
            T--;
        }

        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        System.out.println("Stack [" + stack.size() + "]:");
        for (Integer item : stack) {
            System.out.println(item);
        }

        System.out.println();

        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        queue.dequeue();
        System.out.println("Queue [" + queue.size() + "]:");
        for (Integer item : queue) {
            System.out.println(item);
        }
    }

    @SuppressWarnings({"unchecked", "unused"})
    private static class ArrayStack<Item> implements Iterable<Item> {
        private Item[] a = (Item[]) new Object[1];
        private int N;

        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }

        public void push(Item item) {
            if (N == a.length) {
                resize(a.length * 2);
            }
            a[N++] = item;
        }

        public Item pop() {
            Item item = a[--N];
            a[N] = null;
            if (N > 0 && N == a.length / 4) {
                resize(a.length / 2);
            }
            return item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new ArrayStackIterator();
        }

        private void resize(int newSize) {
            Item[] resized = (Item[]) new Object[newSize];
            System.arraycopy(a, 0, resized, 0, N);
            a = resized;
        }

        private class ArrayStackIterator implements Iterator<Item> {
            private int i = N;

            @Override
            public boolean hasNext() {
                return i > 0;
            }

            @Override
            public Item next() {
                return a[--i];
            }

            @Override
            public void remove() {
            }
        }
    }

    @SuppressWarnings({"unchecked", "unused"})
    private static class ArrayQueue<Item> implements Iterable<Item> {
        private Item[] a = (Item[]) new Object[1];
        private int N, i;

        public boolean isEmpty() { return N == 0; }
        public int size() { return N; }

        public void enqueue(Item item) {
            if (N == a.length) {
                resize(a.length * 2);
            }
            a[N++] = item;
        }

        public Item dequeue() {
            Item item = a[i];
            a[i] = null;
            i++; N--;
            if (N > 0 && N == a.length / 4) {
                resize(a.length / 2);
                i = 0;
            }
            return item;
        }

        @Override
        public Iterator<Item> iterator() {
            return new ArrayQueueIterator();
        }

        private void resize(int newSize) {
            Item[] resized = (Item[]) new Object[newSize];
            System.arraycopy(a, 0, resized, 0, N);
            a = resized;
        }

        private class ArrayQueueIterator implements Iterator<Item> {
            private int x = i;

            @Override
            public boolean hasNext() {
                return x < N + i;
            }

            @Override
            public Item next() {
                return a[x++];
            }

            @Override
            public void remove() {
            }
        }
    }
}
