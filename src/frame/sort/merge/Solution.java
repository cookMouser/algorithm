package frame.sort.merge;


/**
 * 剑指offer 51 数组中的逆序对
 */
public class Solution {

    public int reversePairs(int[] nums) {
        int[] temp = new int[nums.length];

        return sort(nums, 0, nums.length - 1, temp);
    }


    // 排序并返回找到的逆序对
    private int sort(int[] arr, int left, int right, int[] temp) {
        int res = 0;
        if (right >= left) {
            return res;
        }

        int mid = left + (right - left) / 2;
        res += sort(arr, left, mid, temp);
        res += sort(arr, mid + 1, right, temp);
        // 优化： 本身有序，则不需要合并
        if (arr[mid] > arr[mid + 1]) {
            res += merge(arr, left, mid, right, temp);
        }
        return res;
    }

    // 合并两个有序区间 arr[l, mid]
    private int merge(int[] arr, int left, int mid, int right, int[] temp) {
        int res = 0;
        System.arraycopy(arr, left, temp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i <= mid && j <= right) {
                if (temp[i] < temp[j]) {
                    arr[k] = temp[i];
                    i++;
                } else {
                    // 记录逆序对
                    res += mid - left + 1;
                    arr[k] = temp[j];
                    j++;
                }
            } else if (i > mid) {
                arr[k] = temp[j];
                j++;
            } else {
                arr[k] = temp[i];
                i++;
            }
        }
        return res;
    }
}
