package shuati.trackBack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class lc93 {

    private List<String> res = new ArrayList<>();
    private List<String> list = new LinkedList<>();

    public List<String> restoreIpAddresses(String s) {
        backTrack(s, 0);
        return res;
    }

    private void backTrack(String s, int index) {
        if (index == s.length() && list.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    sb.append(list.get(i));
                } else {
                    sb.append(list.get(i)).append(".");
                }
            }
            res.add(sb.toString());
            return;
        }

        if (index >= s.length() || list.size() >= 4) {
            return;
        }

        // 切割一位
        list.add(s.substring(index, index + 1));
        backTrack(s, index + 1);
        list.remove(list.size() - 1);


        if (s.charAt(index) != '0') {
            // 切割两位
            if (index + 1 < s.length()) {
                list.add(s.substring(index, index + 2));
                backTrack(s, index + 2);
                list.remove(list.size() - 1);
            }

            // 切割三位
            if (index + 2 < s.length() && lt255(s, index)) {
                list.add(s.substring(index, index + 3));
                backTrack(s, index + 3);
                list.remove(list.size() - 1);
            }
        }
    }


    private boolean lt255(String s, int index) {
        int sum = 0;
        int k = 1;
        for (int i = index + 2; i >= index; i--) {
            sum += k * (s.charAt(i) - '0');
            k = 10 * k;
        }
        return sum <= 255;
    }

}
