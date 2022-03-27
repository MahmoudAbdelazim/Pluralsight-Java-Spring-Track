package data_structures;

public class Deque<T> {

    private final DoublyLinkedList<T> store = new DoublyLinkedList<>();

    public void enqueueHead(T value) {
        store.addHead(value);
    }

    public void enqueueTail(T value) {
        store.addTail(value);
    }

    public T dequeueHead() {
        if (store.size() == 0) return null;
        T value = store.getFirst();
        store.removeHead();
        return value;
    }

    public T dequeueTail() {
        if (store.size() == 0) return null;
        T value = store.getLast();
        store.removeTail();
        return value;
    }

    public T peekHead() {
        return store.getFirst();
    }

    public T peekTail() {
        return store.getLast();
    }

    public int size() {
        return store.size();
    }
}
