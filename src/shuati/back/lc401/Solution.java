package shuati.back.lc401;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class Solution {

    private List<List<Integer>> res = new ArrayList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public List<String> readBinaryWatch(int turnedOn) {
        int[] arr = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        allRes(arr, turnedOn);
        List<String> time = new ArrayList<>();
        if (res.size() > 0) {
            for (List<Integer> l : res) {
                int h = 0;
                int m = 0;
                for (Integer i : l) {
                    if (i >= 0 && i <= 3) {
                        h += arr[i];
                    } else {
                        m += arr[i];
                    }
                }
                if (h >= 0 && h <= 11 && m >= 0 && m <= 59) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(h).append(":");
                    if (m <= 9) {
                        sb.append('0');
                    }
                    sb.append(m);
                    time.add(sb.toString());
                }
            }
        }
        return time;
    }

    public List<List<Integer>> allRes(int[] arr, int turnedOn) {
        dfs(turnedOn, arr, 0);
        return res;
    }

    private void dfs(int turnOn, int[] arr, int start) {

        if (deque.size() == turnOn) {
            res.add(new ArrayList<>(deque));
            return;
        }

        // 优化
        for (int i = start; i <= arr.length - (turnOn - deque.size()); i++) {
            deque.addLast(i);
            dfs(turnOn, arr, i + 1);
            deque.removeLast();
        }
    }
}
