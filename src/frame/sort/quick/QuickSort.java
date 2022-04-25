package frame.sort.quick;

import java.util.Random;

/**
 *
 */
public class QuickSort {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        Random random = new Random();
        sort(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int left, int right, Random random) {
        // 纯升序的数组，会出现left = -1, right = 0的情况
        // 纯降序的数组，会出现left = arr.length - 1, right = arr.length的情况
        if (right <= left) {
            return;
        }
        int partition = partition(arr, left, right, random);
        sort(arr, left, partition - 1, random);
        sort(arr, partition + 1, right, random);
    }


    /**
     * 比mergeSort性能更好，因为处理的过程更简单
     */
    private static <E extends Comparable<E>> int partition(E[] arr, int left, int right, Random random) {
        // 优化数组已排好序的情况
        // 生成[l, r]之间的随机索引 l + [0, r - l]
        int p = left + random.nextInt(right - left + 1);
        swap(arr, p , left);

        int j = left;

        // arr[l + 1, j] < V ; arr[j + 1, i) >= V
        for (int i = left + 1; i <= right; i++) {
            if (arr[i].compareTo(arr[left]) < 0) {
                swap(arr, j + 1, i);
                j++;
            }
        }
        swap(arr, left, j);
        return j;
    }

    private static <E extends Comparable<E>> void swap(E[] data, int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {

    }
}
