package play.six;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TraversalWithStack {

    // 前序遍历：中左右
    List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return null;
        }
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Action.GO, root));
        while (!stack.isEmpty()) {
            Command pop = stack.pop();
            if (pop.action.equals(Action.PRINT)) {
                result.add(pop.node.value);
            } else {
                if (pop.node.rightChild != null) {
                    stack.push(new Command(Action.GO, pop.node.rightChild));
                }
                if (pop.node.leftChild != null) {
                    stack.push(new Command(Action.GO, pop.node.leftChild));
                }
                stack.push(new Command(Action.PRINT, pop.node));
            }
        }
        return result;
    }

    // 中序遍历：左中右
    List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return null;
        }
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Action.GO, root));
        while (!stack.isEmpty()) {
            Command pop = stack.pop();
            if (pop.action.equals(Action.PRINT)) {
                result.add(pop.node.value);
            } else {
                if (pop.node.rightChild != null) {
                    stack.push(new Command(Action.GO, pop.node.rightChild));
                }
                stack.push(new Command(Action.PRINT, pop.node));
                if (pop.node.leftChild != null) {
                    stack.push(new Command(Action.GO, pop.node.leftChild));
                }
            }
        }
        return result;
    }

    // 后序遍历：左右中
    List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return null;
        }
        Stack<Command> stack = new Stack<>();
        stack.push(new Command(Action.GO, root));
        while (!stack.isEmpty()) {
            Command pop = stack.pop();
            if (pop.action.equals(Action.PRINT)) {
                result.add(pop.node.value);
            } else {
                stack.push(new Command(Action.PRINT, pop.node));
                if (pop.node.rightChild != null) {
                    stack.push(new Command(Action.GO, pop.node.rightChild));
                }
                if (pop.node.leftChild != null) {
                    stack.push(new Command(Action.GO, pop.node.leftChild));
                }
            }
        }
        return result;
    }
}

enum Action {
    GO,PRINT
}

class Command {
    Action action; // print代表打印当前值；go代表递归到另一个节点
    TreeNode node;

    Command(Action action, TreeNode node) {
        this.action = action;
        this.node = node;
    }
}

class TreeNode {
    int value;
    TreeNode leftChild;
    TreeNode rightChild;

    TreeNode(int value) {
        this.value = value;
    }
}
