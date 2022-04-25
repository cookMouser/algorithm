package frame.sort.merge;

import java.util.Arrays;

/**
 * 优化
 */
public class MergeSort2 {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort(arr, 0 ,arr.length - 1, temp);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int left, int right, E[] temp) {
//        if (right >= left) {
//            return;
//        }

        // 优化：区间小的时候，merge过程中进行多次比对和赋值操作，可能直接使用插入排序性能更好
        if (right - left < 15) {
            insertSort(arr, left, right);
        }
        int mid = left + (right - left) / 2;
        sort(arr, left, mid, temp);
        sort(arr, mid + 1, right, temp);
        // 优化： 本身有序，则不需要合并
        if (arr[mid].compareTo(arr[mid + 1]) > 0) {
            merge(arr, left, mid, right, temp);
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
