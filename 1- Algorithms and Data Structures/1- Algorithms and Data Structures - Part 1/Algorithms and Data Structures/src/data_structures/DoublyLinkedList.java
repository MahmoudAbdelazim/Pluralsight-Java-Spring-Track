package data_structures;

import java.util.Objects;

public class DoublyLinkedList<T> {

    public DoublyLinkedListNode<T> head = null;
    public DoublyLinkedListNode<T> tail = null;
    int count = 0;

    public void addHead(T value) {
        DoublyLinkedListNode<T> adding = new DoublyLinkedListNode<>(value);
        adding.setNext(head);
        if (head != null) head.previous = adding;
        head = adding;
        if (tail == null) tail = head;
        count++;
    }

    public void addTail(T value) {
        if (tail == null) {
            addHead(value);
            return;
        }
        DoublyLinkedListNode<T> adding = new DoublyLinkedListNode<>(value);
        tail.next = adding;
        tail = adding;
        count++;
    }

    public void addSorted(T value) {
        if (head == null) {
            head = tail = new DoublyLinkedListNode<>(value);
        } else if (Double.compare((Double) head.value, (Double)value) >= 0) {
            DoublyLinkedListNode<T> newHead = new DoublyLinkedListNode<>(value);
            newHead.next = head;
            head.previous = newHead;
            head = newHead;
        } else if (Double.compare((Double) tail.value, (Double)value) < 0) {
            DoublyLinkedListNode<T> newTail = new DoublyLinkedListNode<>(value);
            tail.next = newTail;
            newTail.previous = tail;
            tail = newTail;
        } else {
            DoublyLinkedListNode<T> insertBefore = head;
            while (Double.compare((Double) insertBefore.value, (Double)value) < 0)
                insertBefore = insertBefore.next;
            DoublyLinkedListNode<T> toInsert = new DoublyLinkedListNode<>(value);
            toInsert.next = insertBefore;
            toInsert.previous = insertBefore.previous;
            insertBefore.previous.next = toInsert;
            insertBefore.previous = toInsert;
        }
        count++;
    }

    public DoublyLinkedListNode<T> find(T value) {
        DoublyLinkedListNode<T> current = head;
        while (current != null) {
            if (current.value.equals(value)) return current;
            current = current.next;
        }
        return null;
    }

    public boolean contains(T value) {
        return find(value) != null;
    }

    public boolean remove(T value) {
        DoublyLinkedListNode<T> node = find(value);
        if (node == null) return false;
        DoublyLinkedListNode<T> next = node.next;
        DoublyLinkedListNode<T> previous = node.previous;
        if (previous == null) {
            head = next;
            if (next != null) head.previous = null;
        } else {
            previous.next = next;
        }
        if (next == null) {
            tail = previous;
            if (previous != null) tail.next = null;
        } else {
            next.previous = previous;
        }
        count--;
        return true;
    }

    public DoublyLinkedListNode<T> getHead() {
        return head;
    }

    private void setHead(DoublyLinkedListNode<T> head) {
        this.head = head;
    }

    public DoublyLinkedListNode<T> getTail() {
        return tail;
    }

    private void setTail(DoublyLinkedListNode<T> tail) {
        this.tail = tail;
    }
}

class DoublyLinkedListNode<T> {

    public T value;
    public DoublyLinkedListNode<T> next = null;
    public DoublyLinkedListNode<T> previous = null;

    public DoublyLinkedListNode(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public DoublyLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode<T> next) {
        this.next = next;
    }

    public DoublyLinkedListNode<T> getPrevious() {
        return previous;
    }

    public void setPrevious(DoublyLinkedListNode<T> previous) {
        this.previous = previous;
    }
}
