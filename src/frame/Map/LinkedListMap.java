package frame.Map;

public class LinkedListMap<K, V> implements Map<K, V>{

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }

    private Node dummyHead = new Node();
    private int size;

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key)) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node == null) {
            Node newNode = new Node(key, value);
            newNode.next = dummyHead.next;
            dummyHead.next = newNode;
            size++;
        } else {
            node.value = value;
        }
    }

    // 与bobo写的不一致
    @Override
    public V remove(K key) {
        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.key.equals(key)) {
                Node delNode = pre.next;
                pre.next = delNode.next;
                delNode.next = null;
                size--;
                return delNode.value;
            }
            pre = pre.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else {
            throw new IllegalArgumentException(key + "not exist");
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
