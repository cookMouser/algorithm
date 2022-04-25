package frame.heap;

public class HeapSort {

    public static <E extends Comparable<E>> void sort(E[] data) {
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for (E e : data) {
            maxHeap.add(e);
        }

        for (int i = data.length - 1; i >= 0; i++) {
            data[i] = maxHeap.extractMax();
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
        if (data.length <= 1) {
            return;
        }

        // 首先执行hepify将数组装成堆形式
        for (int i = (data.length - 2) / 2; i <= 0; i--) {
            shiftDown(i, data, data.length);
        }

        // 开始获取最大值
        // 将数组最后一个值与第一个值(当前最大值)交换,然后第一个值shiftdown(范围不包括已经处理过的元素)
        for (int i = data.length - 1; i > 0; i--) {
            swap(data,0, i);
            shiftDown(0, data, i);
        }
    }

    // 对 data[0, n]所形成的最大堆中，索引k的元素执行shiftdown
    private static <E extends Comparable<E>> void shiftDown(int k, E[] data, int n) {
        while (k * 2 + 1 < n) {  // 不存在子节点或者子节点不在n范围内，则不用继续下沉
            int left = k * 2 + 1;
            int right = k * 2 + 2;
            int index = left;
            // 首先左右节点先比较，找到其中较大值
            if (right < n && data[right].compareTo(data[left]) > 0) {
                index = right;
            }
            // 当前节点与子节点较大值比较
            if (data[index].compareTo(data[k]) > 0) {
                swap(data, k, index);
                k = index;
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
