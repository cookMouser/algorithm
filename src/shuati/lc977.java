package shuati;

public class lc977 {

    // 数组当前的最大值一定在两端
    public int[] sortedSquares(int[] nums) {
        int[] newNums = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;
        int k = nums.length - 1;
        while (left <= right) {
            if (nums[left] * nums[left] >= nums[right] * nums[right]) {
                newNums[k] = nums[left] * nums[left];
                left++;
            } else {
                newNums[k] = nums[right] * nums[right];
                right--;
            }
            k--;
        }
        return newNums;
    }
}
