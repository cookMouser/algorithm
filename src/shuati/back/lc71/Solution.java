package shuati.back.lc71;

import java.util.ArrayList;
import java.util.List;

/**
 * LC: 17. Letter Combinations of a Phone Number
 * 时间复杂度 3^n 成为 O(2^n)
 */

class Solution {
    // 数字与字母的映射存储好
    private String[] letterMap = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    // 存放结果集
    private List<String> res = new ArrayList<>();

    public List<String> letterCombinations(String digits) {
    // 空字符串校验
        if (digits == null || digits.equals("")) {
            return res;
        }
        if (digits.equals("1")) {
            return res;
        }

        findCombinations(digits, 0, "");
        return res;

    }

    /**
     * 递归寻找
     * @param digits 原始字符串
     * @param index  当前处理的字符位置，可以定位到对应的字符
     * @param s 每一层处理得到的结果串
     */
    private void findCombinations(String digits, int index, String s) {
        // 递归终止条件
        if (index == digits.length()) {
            res.add(s);
            return;
        }

        char c = digits.charAt(index);
        // 特别注意：字符0的ascii值不是数字0
        if (c >= '0' && c <= '9') {
            String letters = letterMap[c - '0'];
            for (int i = 0; i < letters.length(); i++) {
                // 注意细节：这里不能给s重新赋值
                findCombinations(digits, index + 1, s + letters.charAt(i));
            }
        }
    }
}
