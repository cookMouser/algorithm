package frame.BST;

import frame.AVL.AVLTree;

public class AVLSet<E extends Comparable<E>, V> {

    private AVLTree avl;
    public AVLSet() {
        this.avl = new AVLTree();
    }
    public void add(E e) {
        avl.add(e, null);
    }

    public void remove(E e) {
        avl.remove(e);
    }

    public boolean contains(E e) {
        return avl.contains(e);
    }

    public int getSize() {
        return avl.getSize();
    }

    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
