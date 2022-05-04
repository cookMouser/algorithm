package shuati.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 二叉堆的性质
 * 1、二叉堆是一棵完全二叉树（把元素顺序排列成树的形状）-- 可以理解把所有元素一层一层从左向右排列
 *
 * 2、最大堆：堆中某个节点的值总是不大于其父节点的值
 */
public class MaxHeap<E extends Comparable<E>> {

    private List<E> data;

    public MaxHeap(int capacity) {
        data = new ArrayList<>(capacity);
    }

    public MaxHeap() {
        data = new ArrayList<>();
    }

    public MaxHeap(E[] arr) {
        data = Arrays.asList(arr);
        if (data.size() > 1) {
            heapify();
        }
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
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 为了维护二叉堆的性质
     * 第一条：首先要删除掉数组的最后一个元素，然后把最后元素的值赋给堆顶
     * 第二条：下沉
     */
    public E extractMax() {
        assert (data.size() > 0);
        E e = data.get(0);
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        shifDown(0);
        return e;
    }

    public E findMax() {
        assert (data.size() > 0);
        return data.get(0);
    }

    private void shifDown(int k) {
        // 完全二叉树，如果存在右子树，那么一定存在左子树
        // 存在子节点则遍历
        while (k >= 0 && leftChild(k) < data.size()) {
            // 找出左右子节点较大值，如果比较大值小则交换
            int swapIndex = leftChild(k);
            if (rightChild(k) < data.size() && data.get(rightChild(k)).compareTo(data.get(leftChild(k))) > 0) {
                swapIndex = rightChild(k);
            }
            if (data.get(k).compareTo(data.get(swapIndex)) < 0) {
                swap(k, swapIndex);
                k = swapIndex;
            } else {
                // 当前节点比两个子节点都大
                break;
            }

        }
    }

    private void swap(int i, int j) {
        E e = data.get(i);
        data.set(i, data.get(j));
        data.set(j, e);
    }


    private int parent(int index) {
        assert (index > 0);
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        // 这里不做参数校验，后面其他方法还要根据返回值得到是否有左孩子
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 取出最大值，并把新元素加入堆
    public E replace(E e) {
        assert data.size() > 0;
        E res = data.get(0);
        data.set(0, e);
        shifDown(0);
        return res;
    }


    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                System.out.println("fail");
                throw new IllegalArgumentException("Error");
            }
        }
    }

}
