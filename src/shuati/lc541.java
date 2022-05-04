package shuati;

public class lc541 {

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int start = 0;
        for (;start < n; start = start + 2 * k) {
            int left = start;
            int right = (n - start) < k ? n - 1 : start + k - 1;
            while (left < right) {
                char tem = chars[left];
                chars[left] = chars[right];
                chars[right] = tem;
                left++;
                right--;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

}
