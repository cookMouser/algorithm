package frame.BST;

public class BSTSet<E extends Comparable<E>> {

    private BST bst;

    public BSTSet() {
        bst = new BST();
    }

    public void add(E e) {
        bst.add(e);
    }

    public void remove(E e) {
        bst.remove(e);
    }

    public boolean contains(E e) {
        return bst.contains(e);
    }

    public int getSize() {
        return bst.size();
    }

    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
