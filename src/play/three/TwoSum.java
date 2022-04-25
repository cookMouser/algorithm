package play.three;

/**
 * 对撞指针
 * 167 Two Sum II
 *
 * 补充： 125 Valid Palindrome
 *       345 Reverse Vowels of a String
 */
public class TwoSum {

    /**
     * 还有一种解法是二分搜索（有排序特点），遍历的过程中使用二分搜索，最终时间复杂度是nlogn
     * @param arr
     * @param target
     * @return
     */
    int[] twoSum(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {

           if ((arr[left] + arr[right]) == target) {
               return new int[]{left, right};
           } else if ((arr[left] + arr[right]) >target) {
               right--;
           } else {
               left++;
           }
        }
        return null;
    }
}
