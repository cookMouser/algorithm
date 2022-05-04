package shuati.tree;

public class lc108 {

    public TreeNode sortedArrayToBST(int[] nums) {
        return sorted(nums, 0, nums.length - 1);
    }

    private TreeNode sorted(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }

        int mid = left + (right - left) / 2;
        // 左区间转成左子树；右区间转成右子树
        TreeNode cur = new TreeNode(nums[mid]);

        if (left < mid) {
            cur.left = sorted(nums, left, mid - 1);
        }

        if (right > mid) {
            cur.right = sorted(nums, mid + 1, right);
        }

        return cur;
    }
}
