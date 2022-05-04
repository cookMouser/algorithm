package shuati;

import java.util.Stack;

public class lc20 {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c == '(' || c == '{') {
                stack.push(c);
            }
            if (c == ']') {
                // 左括号不够或者不匹配
                if (stack.isEmpty() || stack.pop() != '[') {
                    return false;
                }
            }
            if (c == ')') {
                // 左括号不够或者不匹配
                if (stack.isEmpty() || stack.pop() != '(') {
                    return false;
                }
            }
            if (c == '}') {
                // 左括号不够或者不匹配
                if (stack.isEmpty() || stack.pop() != '{') {
                    return false;
                }
            }
        }

        // 左括号多了
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }
}
