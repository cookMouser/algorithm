package frame.prioprityqueue;

public interface  Queue<E extends Comparable<E>> {
 void enqueue(E e);
 E dequeue();
 E getFront();
 int getSize();
 boolean isEmpty();
}
