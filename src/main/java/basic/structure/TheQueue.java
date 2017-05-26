package basic.structure;

/**
 * Basic structure of a collection of a Queue with FIFO (First In First Out) rule.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class TheQueue {
    public static void main(String[] args) {
        TheQueue app = new TheQueue();
        app.start();
    }

    private void start() {
        int[] nums = new int[]{
                10, 20, 30, 40, 50
        };

        Queue<Integer> queue = new Queue<>();
        System.out.println("queue size is " + queue.size());
        for (int n : nums) {
            queue.enqueue(n);
        }
        System.out.println("queue size is " + queue.size());
        if (!queue.isEmpty()) {
            System.out.println("queue items are: ");
            for (Node<Integer> node = queue.first; node != null; node = node.next) {
                int n = node.item;
                System.out.println("\t - " + n);
            }
            System.out.println("queue sie is " + queue.size());
            while (!queue.isEmpty()) {
                int n = queue.dequeue();
                System.out.println("\t dequeueing " + n + "; now size is " + queue.size());
            }
        }
    }

    private class Node<T> {
        T item;
        Node<T> next;
    }

    private class Queue<T> {
        Node<T> first;
        Node<T> last;
        int n;

        boolean isEmpty() {
            return first == null;
        }

        int size() {
            return n;
        }

        void enqueue(T item) {
            Node<T> node = last;
            last = new Node<>();
            last.item = item;
            if (isEmpty()) first = last;
            else node.next = last;
            n += 1;
        }

        T dequeue() {
            Node<T> node = first;
            first = node.next;
            if (isEmpty()) last = null;
            n -= 1;
            return node.item;
        }
    }
}
