package shuati;


import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class lc239 {

    // 使用最大堆实现。堆没法直接指定删除某一个元素，因此在堆中加入索引的信息，每次窗口滑动时不直接取删除某个元素
    // 而是不断循环取最大值，直到最大值的索引在滑动窗口内，堆里面残留窗口外垃圾数据无关紧要
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 遍历层数
        int c = n - k + 1;
        int[] res = new int[c];
        // 堆里面要存入两项信息，索引和索引对应的值
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });

        // 先处理第一层
        for (int j = 0; j < k; j++) {
            pq.add(new int[]{nums[j], j});
        }
        res[0] = pq.peek()[0];

        for (int i = 1; i < c; i++) {
            pq.add(new int[]{nums[i + k - 1], i + k -1});
            // 只挑选在当前滑动窗口内的值
            // 因为在优先队列中无法简单的直接移除某一个元素（直接移除会超时），采用这种方式
            while (pq.peek()[1] < i) {
                pq.poll();
            }
            res[i] = pq.peek()[0];
        }
        return res;
    }

    // 使用单调递减队列实现
    // 滑动窗口里面存储的是索引，从队首拿出的元素索引不在窗口内，则可以直接排除掉
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int n = nums.length;
        // 遍历层数
        int c = n - k + 1;
        int[] res = new int[c];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while(!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
        }
        res[0] = nums[deque.getFirst()];

        // i 为每一层的起始索引
        for (int i = 1; i < c; i++) {
            while(!deque.isEmpty() && nums[i + k - 1] >= nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i + k - 1);
            while (deque.getFirst() < i) {
                deque.removeFirst();
            }
            res[i] = nums[deque.getFirst()];
        }
        return res;
    }

}
