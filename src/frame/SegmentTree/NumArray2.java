package frame.SegmentTree;

/**
 * leetcode 303 不涉及更新
 * leetcode 307 Range Sum Query
 * 元素中途不会变化，不涉及更新操作, 因此可以一次性预置好数据
 * 大集合减去小集合
 */
public class NumArray2 {

    /**
     * sum[i]存放前i个元素的和， sum[0] = 0
     * sum[i] -- nums[0, i - 1]的和
     */
    private int[] sum;

    private int[] data;

    public NumArray2(int[] nums) {
        this.data = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        // nums[0,j]的和
        return sum[j + 1] - sum[i];
    }

    // 复杂度为O(n)
    public void update(int i, int value) {
//        int tem = value - data[i];
//        data[i] = value;
//        for (int j = i + 1; j < sum.length; j++) {
//            sum[j] += tem;
//        }

        data[i] = value;
        for (int j = i + 1; j < sum.length; j++) {
            sum[j] = sum[j - 1] + data[j - 1];
        }
    }
}
