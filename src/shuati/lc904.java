package shuati;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class lc904 {
    public int totalFruit(int[] fruits) {
        int n = fruits.length;
        // 千万注意，这里要用map
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = -1;
        for (; right < n; right++) {
            if (map.containsKey(fruits[right])) {
                map.put(fruits[right], map.get(fruits[right]) + 1);
            } else {
                map.put(fruits[right], 1);
            }
            while (map.size() > 2) {
                if (map.get(fruits[left]) - 1 == 0) {
                    map.remove(fruits[left]);
                } else {
                    map.put(fruits[left], map.get(fruits[left]) - 1);
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}
