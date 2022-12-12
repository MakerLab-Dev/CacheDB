package edai.CacheDB.utils;

public class Node<T> {
    private T value;
    private Node next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
