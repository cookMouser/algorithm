package shuati.trackBack;

import java.util.ArrayList;
import java.util.List;

public class lc17 {

    private String[] strings = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    private List<String> res = new ArrayList<>();
    private String s = "";

    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return res;
        }
        backTrack(digits, 0);
        return res;
    }

    // start表示剩余元素的起始字符位置
    private void backTrack(String digits, int index) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }

        int curNum = digits.charAt(index) - '0';
        for (int i = 0; i < strings[curNum].length(); i++) {
            s = s + strings[curNum].charAt(i);
            backTrack(digits, index + 1);
            s  = s.substring(0, s.length() - 1);
        }
    }


}
