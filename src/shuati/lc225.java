package shuati;


import java.util.LinkedList;
import java.util.Queue;

public class lc225 {

    private Queue<Integer> queue;

    public lc225() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        return queue.poll();
    }

    public int top() {
        if (empty()) {
            throw new IllegalArgumentException("stack is empty");
        }
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.add(queue.poll());
        }
        int res =  queue.peek();
        // 注意塞回去
        queue.add(queue.poll());
        return res;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}
