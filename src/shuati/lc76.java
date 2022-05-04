package shuati;

import java.util.HashMap;
import java.util.Map;

public class lc76 {

    public String minWindow(String s, String t) {
        Map<Character, Integer> sMap = new HashMap<>();
        Map<Character, Integer> tMap = new HashMap<>();
        int sLength = s.length();
        int tLength = t.length();
        for (int i = 0; i < tLength; i++) {
            if (tMap.containsKey(t.charAt(i))) {
                tMap.put(t.charAt(i), tMap.get(t.charAt(i)) + 1);
            } else {
                tMap.put(t.charAt(i), 1);
            }
        }

        for (int i = 0; i < sLength; i++) {
            if (sMap.containsKey(s.charAt(i))) {
                sMap.put(s.charAt(i), sMap.get(s.charAt(i)) + 1);
            } else {
                sMap.put(s.charAt(i), 1);
            }
        }

        for (Character c : tMap.keySet()) {
            if (!sMap.containsKey(c) || sMap.get(c) < tMap.get(c)) {
                return "";
            }
        }

        int left = 0;
        int right = sLength - 1;
        String res = s;

        for (; left < sLength; left++) {
            char leftChar = s.charAt(left);
            if (!tMap.containsKey(leftChar)) {
                continue;
            }
            if (sMap.get(leftChar) > tMap.get(leftChar)) {
                sMap.put(leftChar, sMap.get(leftChar) - 1);
                continue;
            }
            while (true) {
                char rightChar = s.charAt(right);
                if (!tMap.containsKey(rightChar)) {
                    right--;
                    continue;
                }
                if (sMap.get(rightChar) > tMap.get(rightChar)) {
                    sMap.put(rightChar, sMap.get(rightChar) - 1);
                    right--;
                    continue;
                }
                if (right - left + 1 < res.length()) {
                    res = s.substring(left, right + 1);
                }
                break;
            }
            right++;
            while (right < sLength) {
                if (s.charAt(right) == s.charAt(left)) {
                    break;
                } else {
                    right++;
                }
            }
            if (right == sLength) {
                break;
            }
        }
        return res;
    }
}
