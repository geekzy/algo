package basic.structure;

/**
 * Basic structure of a collection of a Stack with LIFO (Last In First Out) rule.
 *
 * @author Imam Kurniawan (geekzy@gmail.com)
 */
public class TheStack {
    public static void main(String[] args) {
        TheStack app = new TheStack();
        app.start();
    }

    private void start() {
        int[] nums = new int[]{
                10, 20, 30, 40, 50
        };

        Stack<Integer> stack = new Stack<>();
        System.out.println("stack size is " + stack.size());
        for (int n : nums) {
            stack.push(n);
        }
        System.out.println("stack size is " + stack.size());
        if (!stack.isEmpty()) {
            System.out.println("stack items are:");
            for (Node<Integer> node = stack.first; node != null; node = node.next) {
                int n = node.item;
                System.out.println("\t - " + n);
            }
            System.out.println("stack size is " + stack.size());
            while (!stack.isEmpty()) {
                int n = stack.pop();
                System.out.println("\t poping " + n + "; now size is " + stack.size());
            }
        }
    }

    private class Node<T> {
        T item;
        Node<T> next;
    }

    private class Stack<T> {
        Node<T> first;
        int n;

        boolean isEmpty() {
            return first == null;
        }

        int size() {
            return n;
        }

        void push(T item) {
            Node<T> nItem = new Node<>();
            nItem.item = item;
            if (!isEmpty()) nItem.next = first;
            first = nItem;
            n = n + 1;
        }

        T pop() {
            Node<T> pItem = first;
            first = pItem.next;
            n = n - 1;
            return pItem.item;
        }
    }
}
