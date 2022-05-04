package shuati.base;

import frame.Map.Map;

import java.util.ArrayList;
import java.util.List;

public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key; // 用于比较维护成二分搜索树
        public V value;
        public Node left;
        public Node right;
        public int height;
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


    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 维护height
        node.height = Math.max(getHeight(node.left), getHeight(node.right));

        // 维护平衡
        if (Math.abs(getBalanceFactor(node)) > 1) {
            // LL
            if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) >= 0) {
                return rightRotate(node);
            }

            // RR
            if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) <= 0) {
                return leftRotate(node);
            }

            // LR
            if (getBalanceFactor(node) > 1 && getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }

            // RL
            if (getBalanceFactor(node) < -1 && getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }
        return node;
    }

    @Override
    public V remove(K key) {
        Node node = remove(root, key);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node remove(Node node, K key) {
        if (node == null) { // 没有找到
            return null;
        }

        Node res = node;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {
            // 删除自身
            if (node.left == null) {
                size--;
                res =  node.right;
            } else if (node.right == null) {
                size--;
                res = node.left;
            } else {
                Node newNode = min(node.right);
                newNode.left = node.left;
                // 不用removemin是为了不在removemin中维护平衡
                newNode.right = remove(node.right, newNode.key);
                res = newNode;
            }
        }

        if (res == null) {
            return null;
        }

        // 维护height
        res.height = Math.max(getHeight(res.left), getHeight(res.right));

        // 维护平衡
        if (Math.abs(getBalanceFactor(res)) > 1) {
            // LL
            if (getBalanceFactor(res) > 1 && getBalanceFactor(res.left) >= 0) {
                return rightRotate(res);
            }

            // RR
            if (getBalanceFactor(res) < -1 && getBalanceFactor(res.right) <= 0) {
                return leftRotate(res);
            }

            // LR
            if (getBalanceFactor(res) > 1 && getBalanceFactor(res.left) < 0) {
                res.left = leftRotate(res.left);
                return rightRotate(res);
            }

            // RL
            if (getBalanceFactor(res) < -1 && getBalanceFactor(res.right) > 0) {
                res.right = rightRotate(res.right);
                return leftRotate(res);
            }
        }
        return res;
    }

    // 如果是查询返回的就是查询到的node；如果是操作类，返回的就是子树的新的根节点
    private Node removeMin(Node node) {
        if (node.left != null) {
            node.left = removeMin(node.left);
            return node;
        }
        // 删除自身的话要返回右节点
        size--;
        return node.right;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
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
        if (node == null) { //没找到
            return null;
        }

        if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if (key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
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
            throw new IllegalArgumentException("no key");
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
        List<K> list = new ArrayList<>();
        inOrder(root, list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i).compareTo(list.get(i - 1)) < 0) {
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
        if (node == null) {
            return true;
        }

        if (Math.abs(getBalanceFactor(node)) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点进行右旋操作后，返回旋转后的新的根节点
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T3 = x.right;
        x.right = y;
        y.left = T3;

        // 维护高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;

    }

    // 对节点进行左旋操作后，返回旋转后的新的根节点
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T3 = x.left;
        x.left = y;
        y.right = T3;

        // 维护高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right));
        x.height = Math.max(getHeight(x.left), getHeight(x.right));
        return x;
    }
}
