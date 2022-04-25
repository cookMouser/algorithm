package shuati.dynamic.lc198;

/**
 * LC: 198. House Robber
 * 基础是一个组合问题，然后使用查询优化
 */
class Solution2 {
    private Integer[] arr; // 表示[i, n - 1]范围内元素所能求得的最大和

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        arr = new Integer[nums.length];
        return back(nums, 0);
    }

    private int back(int[] nums, int start) {

        if (arr[start] != null) {
            return arr[start];
        }
        arr[start] = -1;
        for (int i = start; i < nums.length; i++) {
            if ( i + 2 < nums.length) {
                arr[start] = Math.max(arr[start], (nums[i] + back(nums, i + 2)));
            } else {
                arr[start] = Math.max(arr[start], (nums[i]));
            }
        }
        return arr[start];
    }

}
