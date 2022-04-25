package improve.graph.noweight;

import java.util.Vector;

/**
 * 稀疏图 - 邻接表
 */
public class SparseGraph implements Graph{

    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; //有向还是无向
    private Vector<Integer>[] g;

    public SparseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<>();
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    // 添加边
    public void addEdge(int v, int w) {
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        // 此处不需要判断w到v。因为是无向图的话存在w到v必然也存在v到w
        // 加了该判断代表不允许出现平行边。最坏的情况下添加和查询都是O(v)复杂度，暂时允许出现平行边（可以后面统一处理）。
        // 这也是邻接表适合用于稀疏图原因之一
//        if (!hasEdge(v, w)) {
//            m++;
//        }
        // 允许平行边
        g[v].add(w);
        // 考虑自环边。因为可能添加进去了重复元素。而邻接矩阵没有该问题
        if (v != w && !directed) {
            g[w].add(v);
        }
        m++;
    }

    // 判断是否存在边
    public boolean hasEdge(int v, int w) {
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        for( int i = 0 ; i < g[v].size() ; i ++ )
            if( g[v].elementAt(i) == w )
                return true;
        return false;
    }

    // 返回图中某个顶点的所有邻边
    public Iterable<Integer> adj(int v) {
        assert(v >= 0 && v < n);
        return g[v];
    }

    // 显示图的信息
    public void show(){

        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ )
                System.out.print(g[i].elementAt(j) + "\t");
            System.out.println();
        }
    }
}
