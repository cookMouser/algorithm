package shuati.back.lc77;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC: 77. Combinations
 */
class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque deque = new LinkedList();

    private boolean[] visited;
    public List<List<Integer>> combine(int n, int k) {
        assert n>=k;
        visited = new boolean[n];
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        back(arr, 0, k, 0);
        return res;
    }

    /**
     *
     * @param arr 原始数据
     * @param depth 深度 [0, k)
     */
    private void back(int[] arr, int depth, int k, int lastValue) {
        if (depth == k) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            // 保持升序则不会出现重复排列
            if (!visited[i] && arr[i] > lastValue) {
                visited[i] = true;
                deque.addLast(arr[i]);
                back(arr, depth + 1, k, arr[i]);
                visited[i] = false;
                deque.removeLast();
            }
        }
    }

}
