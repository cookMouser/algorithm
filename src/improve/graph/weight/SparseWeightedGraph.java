package improve.graph.weight;

import java.util.Vector;

/**
 * 稀疏图 - 邻接表
 */
public class SparseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; //有向还是无向
    private Vector<Edge>[] g;

    public SparseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        this.g = new Vector[n];
        for (int i = 0; i < n; i++) {
            g[i] = new Vector<Edge>();
        }
    }

    public int V() {
        return n;
    }

    public int E() {
        return m;
    }

    // 添加边
    public void addEdge(Edge edge) {
        int v = edge.v();
        int w = edge.w();
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        // 注意, 由于在邻接表的情况, 查找是否有重边需要遍历整个链表
        // 我们的程序允许重边的出现
        g[v].add(edge);
        // 考虑自环边。因为可能添加进去了重复元素。而邻接矩阵没有该问题
        if (v != w && !directed) {
            g[w].add(new Edge(w, v, edge.wt()));
        }
        m++;
    }

    // 判断是否存在边
    public boolean hasEdge(int v, int w) {
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        for( int i = 0 ; i < g[v].size() ; i ++ )
            if( g[v].elementAt(i).other(v) == w)
                return true;
        return false;
    }

    // 返回图中某个顶点的所有邻边
    public Iterable<Edge> adj(int v) {
        assert(v >= 0 && v < n);
        return g[v];
    }

    // 显示图的信息
    public void show(){

        for( int i = 0 ; i < n ; i ++ ){
            System.out.print("vertex " + i + ":\t");
            for( int j = 0 ; j < g[i].size() ; j ++ ){
                Edge e = g[i].elementAt(j);
                System.out.print( "( to:" + e.other(i) + ",wt:" + e.wt() + ")\t");
            }
            System.out.println();
        }
    }
}
