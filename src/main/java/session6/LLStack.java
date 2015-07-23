package session6;

public class LLStack {

    private Node first;

    private class Node {
        String item;
        Node next;
    }

    public void push(String s) {
        Node oldFirst = first;
        first = new Node();
        first.item = s;
        first.next = oldFirst;
    }

    public Node pop() {
        Node item = first;
        first = item.next;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public static void main(String[] args) {
        LLStack app = new LLStack();
        System.out.println("initially : " + (app.isEmpty() ? "empty" : "not empty"));
        app.push("test 1");
        app.push("test 2");
        app.push("test 3");
        System.out.println("after push: " + (app.isEmpty() ? "empty" : "not empty"));
        app.pop();
    }
}