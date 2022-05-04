package shuati;

import java.util.*;

public class lc347 {

    private class Frequent implements Comparable<Frequent> {
        private int value; // 整数值
        private int count; // 数量

        public Frequent(int value, int count) {
            this.value = value;
            this.count = count;
        }

        @Override
        public int compareTo(Frequent o) {
            return this.count - o.count;
        }

        public int getValue() {
            return value;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else {
                map.put(nums[i], 1);
            }
        }
        // 千万注意默认是升序
        PriorityQueue<Frequent> priorityQueue = new PriorityQueue(Comparator.reverseOrder());
        for (Integer key : map.keySet()) {
            priorityQueue.add(new Frequent(key, map.get(key)));
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = priorityQueue.poll().getValue();
        }
        return res;
    }

    // 优化
    public int[] topKFrequent2(int[] nums, int k) {
        // 使用字典，统计每个元素出现的次数，元素为键，元素出现的次数为值
        HashMap<Integer,Integer> map = new HashMap();
        for(int num : nums){
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        // 遍历map，用最小堆保存频率最大的k个元素
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return map.get(a) - map.get(b);
            }
        });
        for (Integer key : map.keySet()) {
            if (pq.size() < k) {
                pq.add(key);
            } else if (map.get(key) > map.get(pq.peek())) {
                pq.remove();
                pq.add(key);
            }
        }
        // 取出最小堆中的元素
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = pq.remove();
        }
        return res;
    }
}
