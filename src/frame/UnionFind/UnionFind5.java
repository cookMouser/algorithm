package frame.UnionFind;

import java.util.Random;

/**
 * parent数组
 * 路径压缩
 */
public class UnionFind5 implements UF {

    private int[] parent;
    private int[] rank; //记录每个树的高度，索引为root的索引

    public UnionFind5(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;  //保证了初始情况下parent数组中每个节点单独成树，根节点为自己
            rank[i] = 1;
        }
    }
    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p对应的集合编号
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (parent[p] != p) {
            // 路径压缩。同时也直接跳过了原来的parent
            // 此处没有维护每个树的rank，因为维护精准的rank代价太大，没有必要
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并集合
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        } else { //相等时
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }


}
