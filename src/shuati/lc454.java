package shuati;

import java.util.HashMap;
import java.util.Map;

public class lc454 {

    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count = 0;
        int n = nums1.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum1 = nums1[i] + nums2[j];
                Integer item = map.get(sum1);
                if (item == null) {
                    map.put(sum1, 1);
                } else {
                    map.put(sum1, item + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum2 = nums3[i] + nums4[j];
                Integer item2 = map.get(sum2 * -1);
                if (item2 != null) {
                    count += item2;
                }
            }
        }
        return count;
    }
}
