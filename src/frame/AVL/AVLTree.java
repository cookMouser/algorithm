package frame.AVL;

import frame.Map.Map;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key; // 用于比较维护成二分搜索树
        public V value; // 附加属性
        public Node left;
        public Node right;
        public int height; // 辅助维护平衡属性

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 把元素添加到以node为根的子树，并返回新根
    private Node add(Node node, K key, V value) {
        if (node == null) {
            Node newNode = new Node(key, value);
            size++;
            return newNode;
        }
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }
        // 更新height
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced");
            // 平衡维护
            // LL模式，右旋平衡
            if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
                return rightRotate(node);
            }
            // RR模式，左旋平衡
            if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
                return leftRotate(node);
            }
            // LR模式，将当前节点的左子节点左旋，然后将当前节点右旋
            if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
            // RR模式，将当前节点的右子节点右旋，然后将当前节点左旋
            if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {
        if (node == null) { //没有查找到
            return null;
        }
        Node retNode;
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node, key);
            retNode =  node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                Node successor = min(node.right);
                // 为了在删除最小元素过程中，仍维持平衡，改为调用remove方法
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null;
                retNode = successor;
            }
        }

        // 注意可能为空的情况
        if (retNode == null) {
            return null;
        }
        // 更新height
        node.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);
        // 平衡维护
        // LL模式，右旋平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            retNode = rightRotate(retNode);
        }
        // RR模式，左旋平衡
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            retNode = leftRotate(retNode);
        }
        // LR模式，将当前节点的左子节点左旋，然后将当前节点右旋
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            retNode = rightRotate(retNode);
        }
        // RR模式，将当前节点的右子节点右旋，然后将当前节点左旋
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            retNode = leftRotate(retNode);
        }
        return retNode;
    }

    // 如果是查询返回的就是查询到的node；如果是操作类，返回的就是子树的新的根节点
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        } else {
            node.left = removeMin(node.left);
            return node;
        }
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            return node.value;
        } else {
            return null;
        }
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.left, key);
        } else {
            return node;
        }
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
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

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 判断二叉树是否为一棵二分搜索树
    // 中序遍历结果是从小到大有序的
    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i).compareTo(keys.get(i - 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node root, List<K> keys) {
        if (root == null) {
            return;
        }
        inOrder(root.left, keys);
        keys.add(root.key);
        inOrder(root.right, keys);
    }

    // 判断二叉树是否是一棵平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以node为根的二叉树是否是一棵平衡二叉树
    private boolean isBalanced(Node node) {
        if (node == null) {  //说明遍历到了最后都是合法的
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点进行右旋操作后，返回旋转后的新的根节点
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        y.left = T3;
        x.right = y;

        // 右旋节点及原左子节点的height可能变化。更新height
        // 要注意维护的先后顺序。此时y低于x
        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }

    // 对节点进行左旋操作后，返回旋转后的新的根节点
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        y.right = T3;
        x.left = y;

        // 左旋节点及原右子节点的height可能变化。更新height
        // 要注意维护的先后顺序。此时y低于x
        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
}
