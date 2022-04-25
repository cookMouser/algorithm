package frame.heap;

import java.util.*;

/**
 * 二叉堆的性质
 * 1、二叉堆是一棵完全二叉树（把元素顺序排列成树的形状）-- 可以理解把所有元素一层一层从左向右排列
 *
 * 2、最大堆：堆中某个节点的值总是不大于其父节点的值
 */
public class MinHeap<E extends Comparable<E>> {

    private List<E> data;

    public MinHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MinHeap() {
        data = new ArrayList<>();
    }

    public MinHeap(E[] arr) {
        data = Arrays.asList(arr);
        heapify();
    }

    private void heapify() {
        int parent = parent(data.size() - 1);
        for (int i = parent; i >= 0; i--) {
            shifDown(i);
        }
    }

    public int size() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 为了维护二叉堆的性质
     * 第一条：首先将元素加入到数组最后
     * 第二条：上浮元素
     * @param e
     */
    public void add(E e) {
        data.add(e);
        shiftUp(data.size() - 1);
    }

    // 上浮
    private void shiftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) > 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 为了维护二叉堆的性质
     * 第一条：首先要删除掉数组的最后一个元素，然后把最后元素的值赋给堆顶
     * 第二条：下沉
     */
    public E extractMin() {
        E ret = findMin();
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        shifDown(0);
        return ret;
    }

    public E findMin() {
        if (data.size() == 0) {
            throw  new IllegalArgumentException("heap is empty");
        }
        return data.get(0);
    }

    private void shifDown(int k) {
        while (leftChild(k) < data.size()) {  // 不存在子节点，则不用继续下沉
            int left = leftChild(k);
            int right = rightChild(k);
            int index = left;
            // 首先左右节点先比较，找到其中较大值
            if (right < data.size() && data.get(right).compareTo(data.get(left)) < 0) {
                index = right;
            }
            // 当前节点与子节点较大值比较
            if (data.get(index).compareTo(data.get(k)) < 0) {
                swap(k, index);
                k = index;
            } else {
                break;
            }

        }
    }

    private void swap(int i, int j) {
        if (i < 0 || i >= data.size() || j < 0 || j >= data.size()) {
            throw new IllegalArgumentException("index is illegal");
        }
        E tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }


    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 no parent");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 取出最小值，并把新元素加入堆
    public E replace(E e) {
        E max = findMin();
        data.set(0, e);
        shifDown(0);
        return max;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int n = 1000000;
        MinHeap<Integer> maxHeap = new MinHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMin();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                System.out.println("fail");
                throw new IllegalArgumentException("Error");
            }
        }
    }

}
