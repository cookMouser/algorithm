package improve.graph.weight;

// 图的接口
public interface WeightedGraph<Weight extends Number & Comparable> {

    public int V();
    public int E();
    public void addEdge(Edge<Weight> edge);
    boolean hasEdge( int v , int w );
    void show();
    public Iterable<Edge<Weight>> adj(int v);
}
