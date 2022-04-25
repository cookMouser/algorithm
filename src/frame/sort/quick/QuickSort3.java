package frame.sort.quick;

import frame.sort.ArrayGenerator;
import frame.sort.SortingHelper;

import java.util.Arrays;
import java.util.Random;

/**
 * 三路快排
 */
public class QuickSort3 {

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
        int p = left + random.nextInt(right - left + 1);
        swap(arr, p , left);

        // arr[l + 1, lt] < v; arr[lt + 1, i - 1] == v; arr[gt, r] > v
        int i = left + 1;
        int lt = left;
        int gt = right + 1;
        while (i < gt) {
            if (arr[i].compareTo(arr[left]) == 0) {
                i++;
            } else if (arr[i].compareTo(arr[left]) < 0) {
                lt++;
                swap(arr, lt, i);
                i++;
            } else {
                gt--;
                swap(arr, gt, i);
            }
        }
        swap(arr, left, lt);
        // 最终 arr[l, lt - 1] < v; arr[lt, gt - 1] == v; arr[gt, r] > v

        sort(arr, left, lt - 1, random);
        sort(arr, gt, right, random);

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
