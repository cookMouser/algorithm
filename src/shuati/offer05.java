package shuati;

public class offer05 {
    public String replaceSpace(String s) {
        int n = s.length();
        int length = n;
        for (int i =0; i < n; i++) {
            if (s.charAt(i) == ' ') {
                length += 2;
            }
        }
        char[] chars = new char[length];
        int left = n - 1;
        int right = length - 1;
        for (; left >= 0; left--) {
            if (s.charAt(left) == ' ') {
                chars[right] = '0';
                chars[--right] = '2';
                chars[--right] = '%';
            } else {
                chars[right] = s.charAt(left);
            }
            right--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }
}
