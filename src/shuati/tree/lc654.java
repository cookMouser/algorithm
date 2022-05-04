package shuati.tree;

public class lc654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int n = nums.length;
        return construct(nums, 0, n - 1);
    }

    // 在区间[left, right]内构建一棵子树，返回子树的根
    private TreeNode construct(int[] nums, int left, int right) {
        if (left == right) {
            return new TreeNode(nums[left]);
        }

        int maxIndex = findMax(nums, left, right);
        TreeNode root = new TreeNode(nums[maxIndex]);

        if (maxIndex > left) {
            root.left = construct(nums, left, maxIndex - 1);
        }

        if (maxIndex < right) {
            root.right = construct(nums, maxIndex + 1, right);
        }

        return root;
    }

    //  在区间[left, right]内寻找最大值的索引
    private int findMax(int[] nums, int left, int right) {
        int maxIndex = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
