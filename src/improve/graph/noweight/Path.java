package improve.graph.noweight;

import java.util.Stack;
import java.util.Vector;

/**
 * 寻路
 * 求解的是任意路径之一
 */
public class Path {
    private Graph G;
    private int s; // 起始点
    private boolean[] visited;
    private int[] from; // 记录遍历过程中上一个节点值。反向可以回溯找到路径。该方法只能找到其中一条路径，因为元素不会重复遍历

    public Path(Graph G, int s) {
        assert s >= 0 && s < G.V();
        this.G = G;
        this.s = s;
        this.visited = new boolean[G.V()];
        this.from = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            from[i] = -1;
        }

        dfs(s);
    }

    private void dfs(int v) {
        visited[v] = true;
        for (int i : G.adj(v)) {
            if (!visited[i]) {
                from[i] = v;
                dfs(i);
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



}
