package shuati.dynamic.lc213;

import java.util.Arrays;

/**
 * LC: 213. House Robber II
 * 第一个元素和最后一个不能同时存在，拆分成两个数组，最后求最大值
 * 选取的元素涉及一下三种场景：
 * 1、既不包含0，也不包含 length - 1
 * 2、包含0，但不包含length - 1
 * 3、包含length - 1，但不包含0
 * 将0剔除掉，则筛选了上面1、3场景
 * 将length - 1剔除掉，则筛选了上面1、2场景
 * 因此两项包含所有场景，直接取max
 */
class Solution {
    public int rob(int[] nums) {
        // 特殊情况size == 1
        if (nums.length == 1) {
            return nums[0];
        }
        int noFirst = singleRob(Arrays.copyOfRange(nums, 1, nums.length));
        int noLast = singleRob(Arrays.copyOfRange(nums, 0, nums.length - 1));
        return Math.max(noFirst, noLast);
    }

    // 求数组中所有元素rob得到的最大值
    private int singleRob(int[] nums) {
        int m = nums.length;
        int[] dp = new int[m];
        dp[m - 1] = nums[m - 1];

        for (int i = m - 2; i >= 0; i--) {
            for (int j = i; j < m; j++) {
                if (j + 2 < m) {
                    dp[i] = Math.max(dp[i], nums[j] + dp[j + 2]);
                } else {
                    dp[i] = Math.max(dp[i], nums[j]);
                }
            }
        }
        return dp[0];
    }
}
