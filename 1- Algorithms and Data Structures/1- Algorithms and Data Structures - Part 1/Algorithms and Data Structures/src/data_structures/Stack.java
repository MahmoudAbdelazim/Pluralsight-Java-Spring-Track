package data_structures;

public class Stack<T> {

    private final Deque<T> store = new Deque<>();

    public void push(T value) {
        store.enqueueHead(value);
    }

    public T pop() {
        return store.dequeueHead();
    }

    public T peek() {
        return store.peekHead();
    }

    public int size() {
        return store.size();
    }
}
