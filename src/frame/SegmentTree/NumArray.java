package frame.SegmentTree;

/**
 * leetcode 303
 * leetcode 307
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        if (nums.length > 0) {
            //int[]无法自动转型为Integer[]
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            SegmentTree<Integer> segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw  new IllegalArgumentException("segment tree is null");
        }
        return segmentTree.query(i , j);
    }

    // O(logn)
    public void update(int index, int val) {
        if (segmentTree == null) {
            throw  new IllegalArgumentException("segment tree is null");
        }
        segmentTree.set(index, val);
    }
}
