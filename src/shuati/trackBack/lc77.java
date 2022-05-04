package shuati.trackBack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc77 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTrack(n, k, 1);
        return res;
    }

    // start为当前可选择元素的起始值
    private void backTrack(int n, int k, int start) {
        if (deque.size() == k) {
            res.add(new ArrayList<>(deque));
            return;
        }

        // 这里有个剪枝的操作,对于一直循环到n都不可能凑满k个数的不用浪费时间递归了
        for (int i = start; i <= n - (k - deque.size()) + 1; i++) {
            deque.addLast(i);
            backTrack(n, k , i + 1);
            deque.removeLast();
        }
    }
}
