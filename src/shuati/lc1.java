package shuati;

import java.util.HashMap;
import java.util.Map;

public class lc1 {

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        res[0]= -1;
        res[1] = -1;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int i = 0; i <nums.length; i++) {
            Integer other = map.get(target - nums[i]);
            if (other != null && other != i) {
                res[0] = i;
                res[1] = other;
            }
        }
        return res;
    }
}
