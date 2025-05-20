public class Node<T> {

    T data;
    Node<T> left;
    Node<T> right;

    public Node() {
        this(null, null, null);
    }

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> left, Node<T> right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public T getValue() {
        return data;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setValue(T value) {
        data = value;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public String toString(){
        return "" + this.data;
    }
}
