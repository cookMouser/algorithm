package shuati.dynamicPrograming;

public class lc718 {

    public int findLength(int[] nums1, int[] nums2) {
        int n1= nums1.length;
        int n2 = nums2.length;
        // dp[i][j] 表示以nums1[i - 1]、nums2[j - 1]为公共后缀的公共子数组最大长度。i、j代表就是包含的元素的个数
        // 为什么定义成以i-1和j-1为公共后缀，而不使用i和j，是为了简化dp数组初始化过程。如果以i、j直接为底，
        // 因为dp[i][j]依赖dp[i - 1][j - 1]，即当前元素的左上角位置元素，那么dp数组中i为0的行，j为0的列就必须手动初始化。
        // 如果我们以i-1和j-1为公共后缀，那么dp数组中i为0的行，j为0的列的值为0，为数组定义好后的默认值，不需要处理
        int[][] dp = new int[n1 + 1][n2 + 1];

        int res = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    res = Math.max(res, dp[i][j]);
                }
            }
        }
        return res;
    }

    // 滚动数组优化
    public int findLength2(int[] nums1, int[] nums2) {
        int n1= nums1.length;
        int n2 = nums2.length;
        int[] dp = new int[n2 + 1];

        int res = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = n2; j >= 1; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                    res = Math.max(res, dp[j]);
                } else {
                    // 千万注意这里显示赋值0的操作，因为滚动数组是重用空间，要覆盖掉上一轮的值
                    dp[j] = 0;
                }
            }
        }
        return res;
    }
}
