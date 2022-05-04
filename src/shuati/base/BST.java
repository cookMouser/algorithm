package shuati.base;

import sun.security.krb5.internal.ccache.CCacheInputStream;

import java.util.*;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left;
        public Node right;

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
        } else if (e.compareTo(node.e) > 0) {
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
        preOrder(node.left);
        System.out.println(node.e);
        preOrder(node.right);
    }

    public void afterOrder() {
        afterOrder(root);
    }

    // 后序遍历
    private void afterOrder(Node node) {
        if (node == null) {
            return;
        }
        preOrder(node.left);
        preOrder(node.right);
        System.out.println(node.e);
    }

    // 非递归方式的前序遍历
    public void preOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Action.GO, root));
        while (!stack.isEmpty()) {
            Command pop = stack.pop();
            if (pop.action.equals(Action.DO)) {
                System.out.println(pop.node.e);
            } else {
                if (pop.node.right != null) {
                    stack.push(new Command(Action.GO, pop.node.right));
                }
                if (pop.node.left != null) {
                    stack.push(new Command(Action.GO, pop.node.left));
                }
                stack.push(new Command(Action.DO, pop.node));
            }
        }
    }

    // 非递归方式的中序遍历
    public void inOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Action.GO, root));
        while (!stack.isEmpty()) {
            Command pop = stack.pop();
            if (pop.action.equals(Action.DO)) {
                System.out.println(pop.node.e);
            } else {
                if (pop.node.right != null) {
                    stack.push(new Command(Action.GO, pop.node.right));
                }
                stack.push(new Command(Action.DO, pop.node));
                if (pop.node.left != null) {
                    stack.push(new Command(Action.GO, pop.node.left));
                }
            }
        }
    }

    // 非递归方式的后序遍历
    public void afterOrderNR() {
        if (root == null) {
            return;
        }

        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Action.GO, root));
        while (!stack.isEmpty()) {
            Command pop = stack.pop();
            if (pop.action.equals(Action.DO)) {
                System.out.println(pop.node.e);
            } else {
                stack.push(new Command(Action.DO, pop.node));
                if (pop.node.right != null) {
                    stack.push(new Command(Action.GO, pop.node.right));
                }
                if (pop.node.left != null) {
                    stack.push(new Command(Action.GO, pop.node.left));
                }
            }
        }
    }

    // 层序遍历
    public void centOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node front = queue.poll();
            System.out.println(front.e);
            if (front.left != null) {
                queue.offer(front.left);
            }
            if (front.right != null) {
                queue.offer(front.right);
            }
        }
    }

    // 寻找二叉搜索树的最小值
    public Node minimum() {
        if (root == null) {
            return null;
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
        if (root == null) {
            return null;
        }
        Node minimum = minimum();
        root = removeMinimum(root);
        return minimum;
    }

    // 获取子树删除最小值以后新的根
    private Node removeMinimum(Node node) {
        if (node.left == null) {
            size--;
            return node.right;
        }
        node.left = removeMinimum(node.left);
        return node;
    }


    // 寻找二叉搜索树的最大值
    public Node maxmum() {
        if (root == null) {
            return null;
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
        if (root == null) {
            return null;
        }
        Node maxmum = maxmum(root);
        root = removeMaxmum(root);
        return maxmum;
    }

    // 获取子树删除最大值以后新的根
    private Node removeMaxmum(Node node) {
        if (node.right == null) {
            size--;
            return node.left;
        }
        node.right = removeMaxmum(node.right);
        return node;
    }


    public void remove(E e) {
        remove(root, e);
    }

    // 从一个树中删除一个元素，返回删除后树的新的root
    private Node remove(Node node, E e) {
        if (node == null) {
            // 说明没有找到
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        }

        if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        }

        if (node.left == null) {
            size--;
            return node.right;
        } else if (node.right == null) {
            size--;
            return node.left;
        } else {
            Node succssor = minimum(node.right);
            succssor.left = node.left;
            succssor.right = removeMinimum(node.right);
            return succssor;
        }
    }

    private enum Action {
        GO,
        DO;
    }

    private class Command {
        public Action action;
        public Node node;
        public Command(Action action, Node node) {
            this.action = action;
            this.node = node;
        }
    }

//    private void generateString(Node node, int depth, StringBuilder sb) {
//        if (node == null) {
//            sb.append(generateDepthString(depth) + "null\n");
//        }
//        sb.append(generateDepthString(depth) + node.e + "\n");
//        generateString(node.left, depth + 1, sb);
//        generateString(node.right, depth + 1, sb);
//    }
//
//    private String generateDepthString(int depth) {
//        StringBuilder sb  = new StringBuilder();
//        for (int i = 0; i < depth; i++) {
//            sb.append("--");
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) {
//        BST<Integer> bst = new BST<>();
//        Random random = new Random();
//
//        int n = 1000;
//        for (int i = 0; i < n; i++) {
//            bst.add(random.nextInt(10000));
//        }
//
//        List<Integer> list = new ArrayList<>();
//        while (!bst.isEmpty()) {
//            list.add(bst.removeMinimum().e);
//        }
//        System.out.println(list);
//        for (int i = 1; i < list.size(); i++) {
//            if (list.get(i - 1) > list.get(i)) {
//                throw new IllegalArgumentException("Error");
//            }
//        }
//    }


}
