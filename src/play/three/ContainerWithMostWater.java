package play.three;

/**
 * 11 Container with Most Water
 * 对撞指针搜寻满足要求的两个值，每一次移动的时候一定是可以排除掉被移动的那个值
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            int max2 = Math.min(height[left], height[right])*(right - left);
            if (max2 > max) {
                max = max2;
            }
            // 挪动较小的值。因为较小值决定了高度，那么离它最远的值必定是能组成结果最大值，要进一步筛选那么就可以剔除这个较小值
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }
}
