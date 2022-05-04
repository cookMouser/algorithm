package shuati;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lc15 {


    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int n = nums.length;
        if (n < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            // 忽略掉重复元素。但是为了保证结果集中可以出现相同值，需要保留的是相同元素值的第一个
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                // left加上最大值都太小，排除left
                if (nums[left] + nums[right] + nums[i] < 0) {
                    left++;
                    // right加上最小值都太大，排除right
                } else if (nums[left] + nums[right] + nums[i] > 0) {
                    right--;
                } else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 排除掉重复元素
                    // 注意此处的判断条件顺序，不能出现left + 1超出索引范围
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 此处容易遗漏
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}
