package data_structures;

public class Queue<T> {

    private final Deque<T> store = new Deque<>();

    public void enqueue(T value) {
        store.enqueueTail(value);
    }

    public T dequeue() {
        return store.dequeueHead();
    }

    public T peek() {
        return store.peekHead();
    }

    public int size() {
        return store.size();
    }
}
