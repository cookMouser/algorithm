package frame.sort.shell;

import frame.sort.ArrayGenerator;
import frame.sort.SortingHelper;

import java.util.Arrays;

public class ShellShort {

    public static <E extends Comparable<E>> void sort(E[] data) {
        int h = data.length / 2;
        while (h >= 1) {
            // 分组.start为每组的起始位置
            for (int start = 0; start < h; start++) {

                // 找到每一组的所有元素，做插入排序(从第二个元素开始遍历)操作
                // 每个组内容data[start, start + h, start + 2h...]
                for (int i = start + h; i < data.length; i += h) {
                    E temp = data[i];
                    // 将当前元素与前面每一个元素进行比较，如果不大于则将被比较元素向后挪一位
                    for (int j = i - h; j >= 0; j -= h) {
                        if (temp.compareTo(data[j]) < 0) {
                            data[j + h] = data[j];
                        } else {
                            data[j + h] = temp;
                            break;
                        }
                    }
                }
            }

            // 分组间隔
            h = h /2;
        }
    }

    // 使用不同的步长序列
    public static <E extends Comparable<E>> void sort2(E[] data) {
        // 只要最后h可以等于1，那么步长都是合法的
        int h = 1;
        while (h < data.length) {
            h = h * 3 + 1;
        }

        while (h >= 1) {
            // 实际上h后的每一个元素都需要进行一遍插入排序的比较操作（跟自己组内的元素比较）
            // 不需要把每个组的遍历拆开一个一个组去比较
            for (int i = h; i < data.length; i++) {
                E temp = data[i];
                // 将当前元素与前面每一个元素进行比较，如果不大于则将被比较元素向后挪一位
                for (int j = i - h; j >= 0; j -= h) {
                    if (temp.compareTo(data[j]) < 0) {
                        data[j + h] = data[j];
                    } else {
                        data[j + h] = temp;
                        break;
                    }
                }
            }
            // 分组间隔
            h = h /3;
        }
    }

    public static void main(String[] args) {
        int n = 100000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort", arr);
        SortingHelper.sortTest("ShellSort", arr2);
        SortingHelper.sortTest("ShellSort2", arr3);
    }
}
