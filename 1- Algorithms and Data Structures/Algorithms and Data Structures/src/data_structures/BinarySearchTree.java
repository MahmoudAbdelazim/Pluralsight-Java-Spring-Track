package data_structures;

public class BinarySearchTree<T extends Comparable<T>> {

    BSTNode<T> root = null;
    int count = 0;

    public void add(T value) {
        if (root == null) root = new BSTNode<>(value);
        else addTo(root, value);
        count++;
    }

    private void addTo(BSTNode<T> node, T value) {
        if (value.compareTo(node.getValue()) < 0) {
            if (node.getLeft() == null) node.setLeft(new BSTNode<>(value));
            else addTo(node.getLeft(), value);
        } else {
            if (node.getRight() == null) node.setRight(new BSTNode<>(value));
            else addTo(node.getRight(), value);
        }
    }

    public void preOrderTraversal() {
        preOrderTraversal(root);
    }

    private void preOrderTraversal(BSTNode<T> node) {
        if (node == null) return;
        System.out.println(node.getValue());
        preOrderTraversal(node.getLeft());
        preOrderTraversal(node.getRight());
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(BSTNode<T> node) {
        if (node == null) return;
        inOrderTraversal(node.getLeft());
        System.out.println(node.getValue());
        inOrderTraversal(node.getRight());
    }

    public void postOrderTraversal() {
        postOrderTraversal(root);
    }

    private void postOrderTraversal(BSTNode<T> node) {
        if (node == null) return;
        postOrderTraversal(node.getLeft());
        postOrderTraversal(node.getRight());
        System.out.println(node.getValue());
    }

    public BSTNode<T> search(T value) {
        return search(root, value);
    }

    private BSTNode<T> search(BSTNode<T> node, T value) {
        if (node == null) return null;
        if (value.compareTo(node.getValue()) == 0) return node;
        else if (value.compareTo(node.getValue()) < 0) return search(node.getLeft(), value);
        return search(node.getRight(), value);
    }
}

class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>>{

    private T value;
    private BSTNode<T> left = null;
    private BSTNode<T> right = null;

    public BSTNode(T data) {
        this.value = data;
    }

    public BSTNode(T data, BSTNode<T> left, BSTNode<T> right) {
        this.value = data;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BSTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    public BSTNode<T> getRight() {
        return right;
    }

    public void setRight(BSTNode<T> right) {
        this.right = right;
    }

    @Override
    public int compareTo(BSTNode<T> o) {
        return value.compareTo(o.getValue());
    }
}
