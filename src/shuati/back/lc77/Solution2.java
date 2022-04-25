package shuati.back.lc77;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * LC: 77. Combinations
 */
class Solution2 {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque deque = new LinkedList();

    public List<List<Integer>> combine(int n, int k) {
        assert n>=k;
        int[] arr = new int[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = i;
        }
        back(n, k, 1);
        return res;
    }



    // 剪枝优化
    private void back(int n, int k, int start) {
        if (deque.size() == k) {
            res.add(new ArrayList<>(deque));
            return;
        }

        // 如果从当前元素开始，把后面所有的元素都加到dequeue中，最后的size都无法到达k的话，那么从此元素之后的元素就没有必要继续处理了
        for (int i = start; i <= n - (k - deque.size()) + 1; i++) {
            deque.addLast(i);
            back(n, k, i + 1 );
            deque.removeLast();
        }
    }

}
