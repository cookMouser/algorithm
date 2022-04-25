package improve.graph.weight;

import improve.graph.noweight.Graph;

import java.util.Vector;

/**
 * 最小生成树
 */
public class KruskalMST<Weight extends Number & Comparable> {

    private Vector<Edge<Weight>> mst;
    private Number mstWeight;


    public KruskalMST(WeightedGraph<Weight> graph) {
        MinHeap<Edge<Weight>> pq = new MinHeap<>(graph.E());

        for (int i = 0; i < graph.V(); i++) {
            for (Edge<Weight> e : graph.adj(i)) {
                // 无向图避免边双向重复插入
                if (e.v() < e.w()) {
                    pq.insert(e);
                }
            }
        }

        UnionFind uf = new UnionFind(graph.V());
        while (!pq.isEmpty() && mst.size() < graph.V() - 1) {
            Edge<Weight> e = pq.extractMin();
            if (uf.isConnected(e.v(), e.w())) {
                continue;
            }
            mst.add(e);
            uf.unionElements(e.v(), e.w());
        }

        mstWeight = mst.elementAt(0).wt();
        for( int i = 1 ; i < mst.size() ; i ++ )
            mstWeight = mstWeight.doubleValue() + mst.elementAt(i).wt().doubleValue();
    }

}
