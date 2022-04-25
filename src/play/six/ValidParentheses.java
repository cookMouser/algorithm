package play.six;

import java.util.Stack;

/**
 * 20 Valid Parentheses
 * 匹配的含义：括号左右一一对应，括号左右数量相等
 */
public class ValidParentheses {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i =0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                // 左括号少于右括号
                if (c == ')' || c == ']' || c == '}') {
                    if (stack.isEmpty()) {
                        return false;
                    }
                }
                if (c == ')') {
                    if (stack.pop() != '(') {
                        return false;
                    }
                }
                if (c == ']') {
                    // 注意这两个判断是不能合在一起的，不然pop执行不到
                    if (stack.pop() != '[') {
                        return false;
                    }
                }
                if (c == '}') {
                    if (stack.pop() != '{') {
                        return false;
                    }
                }
            }
        }
        // 左括号多余右括号
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "(dd[aa]cccc)";
        String s2 = "{(dd[aa]cccc)";
        String s3 = "dd[aa]cccc)";
        System.out.println(isValid(s));
        System.out.println(isValid(s2));
        System.out.println(isValid(s3));
    }
}
