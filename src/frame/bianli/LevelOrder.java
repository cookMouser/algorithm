package frame.bianli;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 */
public class LevelOrder {

    public static List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.value);
            if (node.leftChild != null) {
                queue.offer(node.leftChild);
            }
            if (node.rightChild != null) {
                queue.offer(node.rightChild);
            }
        }
        return result;
    }
    public static List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList();
        queue.offer(new Pair<>(root, 0));
        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            if (pair.getValue() == result.size()) {  //每一轮遍历开始，要创建对应list存放元素
                result.add(new ArrayList<>());
            }
            result.get(pair.getValue()).add(pair.getKey().value);
            if (pair.getKey().leftChild != null) {
                queue.offer(new Pair<>(pair.getKey().leftChild, pair.getValue() + 1));
            }
            if (pair.getKey().rightChild.rightChild != null) {
                queue.offer(new Pair<>(pair.getKey().rightChild, pair.getValue() + 1));
            }
        }
        return result;
    }
}
