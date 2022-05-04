package shuati;

public class lc209 {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int res = nums.length + 1;

        // 寻找以right为结尾的最短子数组
        for (; right < nums.length; right++) {
            sum += nums[right];
            while (left <= right && sum >= target) {
                res = Math.min(res, right - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        if (res != nums.length + 1) {
            return res;
        }else {
            return 0;
        }
    }
}
