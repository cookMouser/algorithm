package shuati;

public class lc35 {

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        if (target < nums[0]) {
            return 0;
        }

        if (target > nums[n - 1]) {
            return n;
        }

        // 区间[left,right]
        int left = 0;
        int right = n - 1;

        int mid = -1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = left + 1;
            } else {
                return mid;
            }
        }

        // 如果没有找到等于的，那么mid是最逼近target的值
        if (target > nums[mid]) {
            return mid + 1;
        } else {
            return mid;
        }
    }
}
