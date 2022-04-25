package frame.sort.bubble;

import frame.sort.ArrayGenerator;
import frame.sort.SortingHelper;

import java.util.Arrays;

public class BubbleSort {

    public static <E extends Comparable<E>> void sort(E[] data) {
        //每一轮循环确定的值
        for (int i = data.length - 1; i > 0; i--) {
            //比较的位置
            for (int j = 0; j < i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                }
            }
        }
    }

    public static <E extends Comparable<E>> void sort2(E[] data) {
        //每一轮循环确定的值
        for (int i = data.length - 1; i > 0; i--) {
            // 优化：上一轮是否有元素swap，如果没有代表前面元素都已经排好序，不需要继续循环
            boolean isSwapped = false;
            //比较的位置
            for (int j = 0; j < i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    isSwapped = true;
                }
            }
            if (!isSwapped) {
                break;
            }
        }
    }

    public static <E extends Comparable<E>> void sort3(E[] data) {
        //每一轮循环确定的值
        for (int i = data.length - 1; i > 0;) {
            // 优化：上一轮遍历中最后swap的位置.说明lastSwap以后的元素都是已经排好序的.

            int lastSwapIndex = 0;
            //比较的位置
            for (int j = 0; j < i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    swap(data, j, j + 1);
                    lastSwapIndex = j;
                }
            }
            // 如果lastSwapIndex为0，代表遍历过程中没有发生swap，或者最后的swap发生在索引为0的位置，
            // 这两种情况下经过此轮遍历数组已经是排好序的。i == 0则会直接跳出循环
            i = lastSwapIndex;
        }
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
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);

        arr = ArrayGenerator.generateOrderedArray(n);
        arr2 = Arrays.copyOf(arr, arr.length);
        arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("BubbleSort", arr);
        SortingHelper.sortTest("BubbleSort2", arr2);
        SortingHelper.sortTest("BubbleSort3", arr3);
    }
}
