package frame.sort.merge;

import java.util.Arrays;

public class MergeSort {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        sort(arr, 0 ,arr.length - 1);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        sort(arr, left, mid);
        sort(arr, mid + 1, right);
        // 本身有序，则不合并
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, left, mid, right);
        }
    }

    // 合并两个有序区间 arr[l, mid]
    private static <E extends Comparable<E>> void merge(E[] arr, int left, int mid, int right) {
        E[] temp = Arrays.copyOfRange(arr, left, right + 1);
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i <= mid && j <= right) {
                if (temp[i - left].compareTo(temp[j - left]) <= 0) {
                    arr[k] = temp[i - left];
                    i++;
                } else {
                    arr[k] = temp[j- left];
                    j++;
                }
            } else if (i > mid) {
                arr[k] = temp[j- left];
                j++;
            } else {
                arr[k] = temp[i - left];
                i++;
            }
        }
    }
}
