package shuati;

public class lc844 {


    public boolean backspaceCompare(String s, String t) {
        return parse(s).equals(parse(t));
    }

    // 还可以考虑使用栈
    private String parse(String s) {
        char[] chars = s.toCharArray();
        int slow = 0;
        for (int fast = 0; fast < chars.length; fast++) {
            if (chars[fast] != '#') {
                chars[slow] = chars[fast];
                slow++;
            } else {
                if (slow > 0) {
                    slow--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < slow; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
