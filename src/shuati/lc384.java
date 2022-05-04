package shuati;

import java.util.HashMap;
import java.util.Map;

public class lc384 {

    public boolean canConstruct(String ransomNote, String magazine) {
        int n = ransomNote.length();
        int[] arr = new int[26];

        for (int i = 0; i < n; i++) {
            arr[ransomNote.charAt(i) - 'a'] += 1;
        }

        for (int i =0; i < magazine.length(); i++) {
            arr[magazine.charAt(i) - 'a'] -= 1;
        }

        for (int i = 0; i < 26; i++) {
            if (arr[i] > 0) {
                return false;
            }
        }
        return true;
    }
}
