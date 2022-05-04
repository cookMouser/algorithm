package shuati;

public class lc26 {
    public int removeDuplicates(int[] nums) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (fast != nums.length - 1 && nums[fast] == nums[fast + 1]) {
                continue;
            }
            nums[slow] = nums[fast];
            slow++;
        }
        return slow;
    }
}
