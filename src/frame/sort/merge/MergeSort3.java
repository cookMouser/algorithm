package frame.sort.merge;

import java.util.Arrays;

/**
 * 自底向上
 */
public class MergeSort3 {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        // 合并的区间长度
        for (int sz = 1; sz < n; sz += sz) {
            // 合并[i, i + sz -1]和[i + sz, Math.Min(i + sz + sz -1, n - 1)]
            for (int i = 0; i + sz < n; i += sz + sz) {
                if (arr[i + sz -1].compareTo(arr[i + sz]) > 0) {
                    merge(arr, i, i + sz - 1, Math.min(i + sz + sz -1, n - 1), temp);
                }
            }
        }
    }


    // 合并两个有序区间 arr[l, mid]
    private static <E extends Comparable<E>> void merge(E[] arr, int left, int mid, int right, E[] temp) {
//        E[] temp = Arrays.copyOfRange(arr, left, right + 1);
        // 优化：不在每次merge时候去创建一个新数组
        System.arraycopy(arr, left, temp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i <= mid && j <= right) {
                if (temp[i].compareTo(temp[j]) <= 0) {
                    arr[k] = temp[i];
                    i++;
                } else {
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
    }

    // 使用插入排序法
    public static <E extends Comparable<E>> void insertSort(E[] arr, int left, int right) {
        for (int i = left; i <= right; i++) {
            E temp = arr[i];
            int j;
            for (j = i; i - 1 >= left; j--) {
                if (temp.compareTo(arr[j - 1]) < 0) {
                    arr[j] = arr[j - 1];
                }
            }
            arr[j] = temp;
        }
    }
}
