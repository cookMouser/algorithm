package improve.graph.noweight;

import java.util.Vector;

/**
 * 稠密图 - 邻接矩阵
 */
public class DenseGraph implements Graph{

    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; //有向还是无向
    private boolean[][] g;

    public DenseGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new boolean[n][n]; // n*n矩阵
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
        if (!hasEdge(v, w)) {
            m++;
        }
        g[v][w] = true;
        if (!directed) {
            g[w][v] = true;
        }
    }

    // 判断是否存在边
    public boolean hasEdge(int v, int w) {
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        return g[v][w];
    }


    // 返回图中某个顶点的所有邻边
    public Iterable<Integer> adj(int v) {
        assert(v >= 0 && v < n);
        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < g[v].length; i++) {
            if (g[v][i]) {
                vector.add(i);
            }
        }
        return vector;
    }

    // 显示图的信息
    public void show(){
        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                System.out.print(g[i][j]+"\t");
            System.out.println();
        }
    }
}
