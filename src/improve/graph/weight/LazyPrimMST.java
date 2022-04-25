package improve.graph.weight;

import improve.graph.noweight.Graph;

import java.util.Vector;

/**
 * 最小生成树
 * @param <Weight>
 */
public class LazyPrimMST<Weight extends Number & Comparable> {

    private WeightedGraph<Weight> G;
    private MinHeap<Edge<Weight>> pq;
    private boolean[] marked; // 标记节点是否被访问
    private Vector<Edge<Weight>> mst; // 最小生成树所有边
    private Number mstWeight; // 最小生成树的权值

    public LazyPrimMST(WeightedGraph G) {
        this.G = G;
        this.pq = new MinHeap<>(G.E());
        this.marked = new boolean[G.V()];
        this.mst = new Vector<Edge<Weight>>();
        // 随机选取一个节点开始
        visit(0);
        while (!pq.isEmpty()) {
            Edge<Weight> e = pq.extractMin();
            // 如果最小边的两个顶点都已经遍历，那么这条边肯定不是切边
            if (marked[e.v()] && marked[e.w()]) {
                continue;
            }
            mst.add(e);
            // 从最小边中未遍历的节点继续遍历
            if (!marked[e.v()]) {
                visit(e.v());
            } else {
                visit(e.w());
            }
        }

        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

    private void visit(int v) {
        assert (!marked[v]);
        marked[v] = true;
        // 访问节点的所有边放入堆
        for (Edge<Weight> item : G.adj(v)) {
            if (!marked[item.other(v)]) {
                pq.insert(item);
            }
        }
    }

    // 返回最小生成树的所有边
    Vector<Edge<Weight>> mstEdges(){
        return mst;
    };

    // 返回最小生成树的权值
    Number result(){
        return mstWeight;
    };
}
