package frame.UnionFind;

import java.util.Random;

/**
 * parent数组
 * 基于size 的优化
 */
public class UnionFind3 implements UF {

    private int[] parent;
    private int[] sz; //记录每个树的元素数量，索引为root的索引

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;  //保证了初始情况下parent数组中每个节点单独成树，根节点为自己
            sz[i] = 1;
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
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
            sz[pRoot] = 0;
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
            sz[qRoot] = 0;
        }
    }

}
