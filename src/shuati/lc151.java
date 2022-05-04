package shuati;

public class lc151 {
    public String reverseWords(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        int start;
        int end;
        for (start = 0; start < n; start++) {
            if (s.charAt(start) != ' ') {
                break;
            }
        }

        for (end = n - 1; end >= 0; end--) {
            if (s.charAt(end) != ' ') {
                break;
            }
        }

        int slow = 0;
        int fast = start;

        for (; fast <= end; fast++) {
            if (chars[fast] == ' ' && chars[fast - 1] == ' ') {
                continue;
            }
            chars[slow] = chars[fast];
            slow++;
        }
        int length = slow;
        reverseRange(chars, 0, slow - 1);

        int left = 0;
        for (int right = 0; right < length; right++) {
            if (chars[right] == ' ') {
                reverseRange(chars, left, right - 1);
                left = right + 1;
                // 千万注意right为最后一个元素的情况
            } else if (right == length - 1) {
                reverseRange(chars, left, right);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();

    }

    private void reverseRange(char[] chars, int left, int right) {
        while (left < right) {
            char tem = chars[left];
            chars[left] = chars[right];
            chars[right] = tem;
            left++;
            right--;
        }
    }

}
