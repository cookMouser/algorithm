package shuati.trackBack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc216 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();
    private int sum = 0;

    public List<List<Integer>> combinationSum3(int k, int n) {
        backTrack(k, n, 1);
        return res;
    }

    private void backTrack(int k, int n, int start) {
        if (deque.size() == k && sum == n) {
            res.add(new ArrayList<>(deque));
            return;
        }

        // 优化
        if (deque.size() == k || sum >= n) {
            return;
        }

        // 优化 如果后面的元素个数加起来都凑不满k，就不用递归了
        for (int i = start; i <= 9 - (k - deque.size()) + 1; i++) {
            deque.addLast(i);
            sum += i;
            backTrack(k, n, i + 1);
            deque.removeLast();
            sum -= i;
        }
    }
}
