package shuati.base;

public class HeapSort {

    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> heap = new MaxHeap<>();
        for (int i = 0; i < data.length; i++) {
            heap.add(data[i]);
        }
        for (int i = data.length - 1; i >= 0; i--) {
            data[i] = heap.extractMax();
        }
    }

    /**
     * 优化的点：
     * 1、所有操作在原数组上进行，没有使用多的空间
     * 2、由数组转为堆的过程中，算法复杂度由O(nlogn)变为O(n)
     * @param data
     * @param <E>
     */
    public static <E extends Comparable<E>> void sortImprove(E[] data) {

        int parent = (data.length - 2) / 2;
        for (int i = parent; i >= 0; i--) {
            shiftDown(i, data, data.length);
        }

        for (int i = data.length - 1; i > 0; i--) {
            swap(data, i, 0);
            shiftDown(0, data, i);
        }

    }

    // 对 data[0, n]所形成的最大堆中，索引k的元素执行shiftdown
    private static <E extends Comparable<E>> void shiftDown(int k, E[] data, int n) {
        while (k >= 0 &&  2 * k + 1 < n) {
            int swapIndex = 2 * k + 1;
            if (2 * k + 2 < n && data[swapIndex].compareTo(data[2 * k + 2]) < 0) {
                swapIndex = 2 * k + 2;
            }
            if (data[k].compareTo(data[swapIndex]) < 0) {
                swap(data, k, swapIndex);
                k = swapIndex;
            } else {
                break;
            }
        }

    }

    private static <E extends Comparable<E>> void swap(E[] data, int i, int j) {
        E tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }
}
