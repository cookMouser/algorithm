package shuati;

public class lc34 {

    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = -1;

        int n = nums.length;
        if (n == 0) {
            return res;
        }

        int left = 0;
        int right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                int start = mid;
                while (start > 0 && nums[start - 1] == target) {
                    start--;
                }
                int end = mid;
                while (end < n - 1 && nums[end + 1] == target) {
                    end++;
                }
                res[0] = start;
                res[1] = end;
                // 千万注意这里要直接返回，不要继续循环二分了
                return res;
            }
        }
        return res;

    }
}
