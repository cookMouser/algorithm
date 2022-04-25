package shuati.back.lc131;


import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 131. Palindrome Partitioning
 */
class Solution {

    private List<List<String>> res = new ArrayList();

    // 存放遍历过程中所有回文串，当向上回溯一层时，需要removelast
    private Deque deque = new LinkedList();
    public List<List<String>> partition(String s) {
        backTrack(s, 0);
        return res;
    }

    /**
     * 两种情况：
     *  1、找到字串都是回文串的集合，最后递归的index为s.length，然后向上回溯
     *  2、寻找过程中由index开始一直找到s.lenght都没有找到一个回文串，递归终止，然后向上追溯
     * @param s 原始字符串
     * @param index 当前处理的索引
     *
     */
    private void backTrack(String s, int index) {

        if (index == s.length()) {
            res.add(new ArrayList<>(deque));
            return;
        }
        // 从当前位置开始寻找回文串
        for (int i = index; i < s.length(); i++) {
            if (isPalid(s, index, i)) {
                deque.addLast(new String(s.toCharArray(), index, i - index + 1));
                backTrack(s, i + 1);
                // 向上一层回溯时，要从队列中去掉当前节点
                deque.removeLast();
            } else {
                continue;
            }
        }
    }

    // 双指针判断是否是回文串
    private boolean isPalid(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
