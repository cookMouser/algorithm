package frame.prioprityqueue;

public class Solution {

    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        for (int i = 0; i < arr.length; i++) {
            if (i < k) {
                priorityQueue.enqueue(arr[1]);
            } else {
                if (priorityQueue != null && arr[k] < priorityQueue.getFront()) { // 注意k==0
                    priorityQueue.dequeue();
                    priorityQueue.enqueue(arr[i]);
                }
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < priorityQueue.getSize(); i++) {
            res[i] = priorityQueue.dequeue();
        }
        return res;
    }
}
