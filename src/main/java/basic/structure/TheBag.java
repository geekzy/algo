package basic.structure;

/**
 * Basic structure of a collection without remove operation, only adds item.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class TheBag {
    public static void main(String[] args) throws Throwable {
        TheBag app = new TheBag();
        app.start();
    }

    private void start() throws Throwable {
        String[] names = new String[]{
                "Imam", "Dita", "Adhwa", "Abdullah"
        };

        Bag<String> bag = new Bag<>();
        System.out.println("bag size is " + bag.size());
        for (String name : names) {
            bag.add(name);
        }
        System.out.println("bag size is " + bag.size());
        if (!bag.isEmpty()) {
            System.out.println("bag items are:");
            for (Node<String> node = bag.first; node != null; node = node.next)
                System.out.println("\t - " + node.item);
        }
    }

    private class Node<T> {
        T item;
        Node<T> next;
    }

    private class Bag<T> {
        Node<T> first;
        int n;

        boolean isEmpty() {
            return first == null;
        }

        int size() {
            return n;
        }

        void add(T item) {
            Node<T> node = new Node<>();
            node.item = item;
            node.next = first;
            first = node;
            n = n + 1;
        }
    }
}
