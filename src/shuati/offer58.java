package shuati;

public class offer58 {
    public String reverseLeftWords(String s, int n) {
        char[] chars = s.toCharArray();
        int length = s.length();
        reverseRange(chars, 0, length - 1);
        reverseRange(chars, length - n, length - 1);
        reverseRange(chars, 0, length - n - 1);
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
