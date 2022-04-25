package frame.sort.quick;

import frame.sort.ArrayGenerator;
import frame.sort.SortingHelper;

import java.util.Arrays;
import java.util.Random;

/**
 * 二路快排
 */
public class QuickSort2 {

    public static <E extends Comparable<E>> void sort(E[] arr) {
        Random random = new Random();
        sort(arr, 0, arr.length - 1, random);
    }

    private static <E extends Comparable<E>> void sort(E[] arr, int left, int right, Random random) {
        // 纯升序的数组，会出现left = -1, right = 0的情况
        // 纯降序的数组，会出现left = arr.length - 1, right = arr.length的情况
        if (left >= right) {
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

        int i = left + 1;
        int j = right;
        // 自己的写法
//        while (i <= j) {
//            if (arr[i].compareTo(arr[left]) > 0 && arr[j].compareTo(arr[left]) < 0) {
//                swap(arr, i, j);
//            }
//            if (arr[i].compareTo(arr[left]) <= 0) {
//                i++;
//            }
//            if (arr[j].compareTo(arr[left]) >= 0) {
//                j--;
//            }
//        }
        while(true) {
            while (i <= j && arr[i].compareTo(arr[left]) < 0) {
                i++;
            }
            while (i <= j && arr[j].compareTo(arr[left]) > 0) {
                j--;
            }
            if (i >= j) {
                break;
            }

            swap(arr, i, j);
            i++;
            j--;
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

        int n = 100000;

        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Random Array");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        System.out.println("---------");

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Ordered Array");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        System.out.println("---------");

        arr = ArrayGenerator.generateRandomArray(n, 1);
        arr2 = Arrays.copyOf(arr, arr.length);

        System.out.println("Same value Array");
        SortingHelper.sortTest("QuickSort", arr);
        SortingHelper.sortTest("QuickSort2Ways", arr2);
        System.out.println("---------");


    }
}
