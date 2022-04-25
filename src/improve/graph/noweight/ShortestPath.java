package improve.graph.noweight;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

/**
 * 广度优先遍历求无权图最短路径
 * 当前求解出来的也只是最短路径之一
 */
public class ShortestPath {

    private Graph G;
    private int s; // 起始点
    private boolean[] visited;
    private int[] from; // 记录遍历过程中上一个节点值。反向可以回溯找到路径。该方法只能找到其中一条路径，因为元素不会重复遍历
    private int[] ord; // 记录节点次序

    public ShortestPath(Graph G, int s) {
        assert s >= 0 && s < G.V();
        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.from = new int[G.V()];
        this.ord = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
            ord[i] = -1;
        }

        Queue<Integer> queue = new LinkedList<>();
        visited[s] = true;
        ord[s] = 0;
        queue.add(s);
        while (!queue.isEmpty()) {
            Integer v = queue.remove();
            for (int i : G.adj(v)) {
                if (!visited[i]) {
                    visited[i] = true;
                    from[i] = v;
                    ord[i] = ord[v] + 1;
                    queue.add(i);
                }
            }
        }
    }


    // s 到w是否有路径
    public boolean hasPath(int w) {
        assert w >= 0 && w < G.V();
        return visited[w];
    }


    // 查询从s点到w点的路径, 存放在vec中
    public Vector<Integer> path(int w) {
        assert hasPath(w);
        Stack<Integer> s = new Stack<>();
        int p = w;
        while (p != -1) {
            s.push(p);
            p = from[p];
        }

        Vector<Integer> vector = new Vector<>();
        while (!s.isEmpty()) {
            vector.add(s.pop());
        }

        return vector;

    }

    // 打印出从s点到w点的路径
    void showPath(int w){

        assert hasPath(w) ;

        Vector<Integer> vec = path(w);
        for( int i = 0 ; i < vec.size() ; i ++ ){
            System.out.print(vec.elementAt(i));
            if( i == vec.size() - 1 )
                System.out.println();
            else
                System.out.print(" -> ");
        }
    }

    // 查看从s点到w点的最短路径长度
    // 若从s到w不可达，返回-1
    int length(int w) {
        return ord[w];
    }
}
