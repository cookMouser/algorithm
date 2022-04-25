package improve.graph.weight;

import java.util.Vector;

/**
 * 稠密图 - 邻接矩阵
 */
public class DenseWeightedGraph<Weight extends Number & Comparable> implements WeightedGraph {

    private int n; // 顶点数
    private int m; // 边数
    private boolean directed; //有向还是无向
    private Edge<Weight>[][] g;

    public DenseWeightedGraph(int n, boolean directed) {
        this.n = n;
        this.m = 0;
        this.directed = directed;
        g = new Edge[n][n]; // n*n矩阵
        for(int i = 0 ; i < n ; i ++)
            for(int j = 0 ; j < n ; j ++)
                g[i][j] = null;
    }

    @Override
    public int V() {
        return n;
    }

    @Override
    public int E() {
        return m;
    }

    @Override
    public void addEdge(Edge edge) {
        int v = edge.v();
        int w = edge.w();
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        if (!hasEdge(v, w)) {
            m++;
        }

        g[v][w] = edge;
        if (!directed) {
            g[w][v] =new Edge(w, v, edge.wt());
        }
    }

    @Override
    public boolean hasEdge(int v, int w) {
        assert(v >= 0 && v < n);
        assert(w >= 0 && w < n);
        return g[v][w] != null;
    }

    @Override
    public void show() {
        for( int i = 0 ; i < n ; i ++ ){
            for( int j = 0 ; j < n ; j ++ )
                if( g[i][j] != null )
                    System.out.print(g[i][j].wt()+"\t");
                else
                    System.out.print("NULL\t");
            System.out.println();
        }
    }

    @Override
    public Iterable<Edge> adj(int v) {
        assert(v >= 0 && v < n);
        Vector<Edge> vector = new Vector<>();

        for (int i = 0; i < g[v].length; i++) {
            if (g[v][i] != null) {
                vector.add(g[v][i]);
            }
        }
        return vector;
    }
}
