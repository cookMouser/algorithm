package play.six;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 71
 * 可以考虑使用一些string自带的方法，会更简单一些
 */
public class SimplifyPath {

    public static String simplify(String s) {
        Stack<String> stack = new Stack<>();
        String temp = "";
        for (int i = 1; i < s.length(); i ++) {
            if (s.charAt(i) == '/') {
                if (temp.length() == 1 && temp.charAt(0) == '.') {
                    temp = "";
                    continue;
                } else if (temp.length() == 2 && temp.charAt(0) == '.' && temp.charAt(1) == '.') {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                } else if (temp.length() != 0) {
                    stack.push(temp);
                }
                temp = "";
            } else {
                temp = temp + s.charAt(i);
            }
        }
        if (temp.length() > 0) {
            stack.push(temp);
        }
        if (!stack.isEmpty()) {
            List<String> list = new ArrayList<>(stack);
            String result = "";
            for (String s1 : list) {
                result = result + '/' + s1;
            }
            return result;
        } else {
            return "/";
        }
    }

    public static void main(String[] args) {
        String s = "/home//hello/";
        String s1 = "/a/./b/../../c/";
        System.out.println(simplify(s));
        System.out.println(simplify(s1));
    }
}
