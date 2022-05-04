package shuati.trackBack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc131 {

    private List<List<String>> res = new ArrayList<>();
    private Deque<String> deque = new LinkedList<>();

    public List<List<String>> partition(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList<>(deque));
            return;
        }

        for (int i = start; i < s.length(); i++) {
            if (isParam(s, start, i)) {
                deque.addLast(s.substring(start, i + 1));
                backTrack(s, i + 1);
                deque.removeLast();
            }
        }
    }


    private boolean isParam(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


}
