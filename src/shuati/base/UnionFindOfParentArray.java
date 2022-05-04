package shuati.base;

import frame.UnionFind.UF;

/**
 * parent数组
 * 路径压缩
 */
public class UnionFindOfParentArray implements UF {

    private int[] parent;
    private int[] rank;

    public UnionFindOfParentArray(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i <size; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }
    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p对应的集合编号
    // 路径压缩优化版本
    private int find(int p) {
        assert p >=0 && p < parent.length;
        while (parent[p] != parent[parent[p]]) {
            parent[p] = parent[parent[p]];
        }
        return parent[p];
    }

    private int find2(int p) {
        assert p >=0 && p < parent.length;
        while (p != parent[p]) {
            p = parent[p];
        }
        return parent[p];
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
        } else if (rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[pRoot] = qRoot;
            rank[qRoot] += 1;
        }
    }


}
