package shuati;

import java.util.Stack;

public class lc232 {

    private Stack<Integer> addStacck;
    private Stack<Integer> delStacck;

    public lc232() {
        addStacck = new Stack<>();
        delStacck = new Stack<>();
    }

    public void push(int x) {
        addStacck.push(x);
    }

    public int pop() {
        if (empty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        if (!delStacck.isEmpty()) {
            return delStacck.pop();
        }
        while (!addStacck.isEmpty()) {
            delStacck.push(addStacck.pop());
        }
        return delStacck.pop();

    }

    public int peek() {
        if (empty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        if (!delStacck.isEmpty()) {
            return delStacck.peek();
        }
        while (!addStacck.isEmpty()) {
            delStacck.push(addStacck.pop());
        }
        return delStacck.peek();
    }

    public boolean empty() {
        return addStacck.isEmpty() && delStacck.isEmpty();
    }
}
