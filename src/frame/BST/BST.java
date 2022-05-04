package frame.BST;

import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        Node left;
        Node right;

        public Node(E e) {
            this.e = e;
        }
    }

    private Node root;
    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 尝试一下非递归操作
//    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//            size++;
//        } else {
//            add(root, e);
//        }
//    }

    // 进方法时候node不能为空。为空的话则找不到上一级，无法将e塞进去
//    private void add(Node node, E e) {
//        if (e.compareTo(node.e) < 0) {
//            if (node.left == null) {
//                node.left = new Node(e);
//                size++;
//            } else {
//                add(node.left, e);
//            }
//        } else if (e.compareTo(node.e) > 0) {
//            if (node.right == null) {
//                node.right = new Node(e);
//                size++;
//            } else {
//                add(node.right, e);
//            }
//        }
//    }

    public void add(E e) {
        root = add(root, e);
    }
    // 递归函数有返回值，可以保持与上一级递归的联系
    // 增加一个参数接收节点，避免与上一轮断开联系
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0){
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            return contains(node.right, e);
        }
        return true;
    }

    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder() {
        inOrder(root);
    }

    // 中序遍历  可以得到从小到大顺序的结果
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void afterOrder() {
        afterOrder(root);
    }

    // 后序遍历
    private void afterOrder(Node node) {
        if (node == null) {
            return;
        }
        afterOrder(node.left);
        afterOrder(node.right);
        System.out.println(node.e);
    }

    // 非递归方式的前序遍历
    public void preOrderNR() {
        if (root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.println(pop.e);
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }

        }
    }

    // 层序遍历
    public void centOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node peek = queue.remove();
            System.out.println(peek.e);
            if (peek.left != null) {
                queue.add(peek.left);
            }
            if (peek.right != null) {
                queue.add(peek.right);
            }
        }
    }

    // 寻找二叉搜索树的最小值
    public Node minimum() {
        if (size == 0) {
            return null; // 或者抛异常
        }
        return minimum(root);
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除二叉搜索树的最小值
    public Node removeMinimum() {
        if (size == 0) {
            return null; // 或者抛异常
        }
        Node minimum = minimum();
        root = removeMinimum(root);
        return minimum;
    }

    // 获取子树删除最小值以后新的根
    private Node removeMinimum(Node node) {
        if (node.left == null) {
//            if (node.right != null) {
//                return node.right;
//            } else {
//                return null;
//            }
            Node rightNode = node.right; // 包含rightnode为null的情况
            node.right = null;
            size--;
            return rightNode;
        } else {
            node.left = removeMinimum(node.left);
            return node;
        }

    }


    // 寻找二叉搜索树的最大值
    public Node maxmum() {
        if (size == 0) {
            return null; // 或者抛异常
        }
        return maxmum(root);
    }

    private Node maxmum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maxmum(node.right);
    }

    // 删除二叉搜索树的最大值
    public Node removeMaxmum() {
        if (size == 0) {
            return null; // 或者抛异常
        }
        Node maxmum = maxmum();
        root = removeMaxmum(root);
        return maxmum;
    }

    // 获取子树删除最大值以后新的根
    private Node removeMaxmum(Node node) {
        if (node.right == null) {
//            if (node.left != null) {
//                return node.left;
//            } else {
//                return null;
//            }
            Node leftNode = node.left; // 包含leftnode为null的情况
            node.left = null;
            size--;
            return leftNode;
        } else {
            node.right = removeMaxmum(node.right);
            return node;
        }

    }


    public void remove(E e) {
        root = remove(root, e);
    }

    // 从一个树中删除一个元素，返回删除后树的新的root
    private Node remove(Node node, E e) {
        if (node == null) { //说明没有找到该元素
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { //匹配上
            if (node.left == null) { // 左子节点为空
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            } else if (node.right == null) {  // 右子节点为空
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            } else { // 左右子节点都不为空
                // 寻找左子节点的最大值（前驱）或者右子节点的最小值（后驱），作为后继来代替原节点。同时要把替代的节点从原位置删除
                Node successor = minimum(node.right);
                // leetcode上要先赋值右节点，再赋值左节点。顺序颠倒会报错
                successor.right = removeMinimum(node.right);  // 这里面已经做了一次size--，所以删除node以后，不需要再次size--
                successor.left = node.left;
                node.left = null;
                node.right = null;
                return successor;
            }
        }
    }

    private void generateString(Node node, int depth, StringBuilder sb) {
        if (node == null) {
            sb.append(generateDepthString(depth) + "null\n");
        }
        sb.append(generateDepthString(depth) + node.e + "\n");
        generateString(node.left, depth + 1, sb);
        generateString(node.right, depth + 1, sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder sb  = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            sb.append("--");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        Random random = new Random();

        int n = 1000;
        for (int i = 0; i < n; i++) {
            bst.add(random.nextInt(10000));
        }

        List<Integer> list = new ArrayList<>();
        while (!bst.isEmpty()) {
            list.add(bst.removeMinimum().e);
        }
        System.out.println(list);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i - 1) > list.get(i)) {
                throw new IllegalArgumentException("Error");
            }
        }
    }


}
