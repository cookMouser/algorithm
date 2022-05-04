package shuati.base;

import frame.UnionFind.UF;

/**
 * id数组
 */
public class UnionFindOfIdArray implements UF {

    private int[] id;

    public UnionFindOfIdArray(int size) {
        id = new int[size];
        for (int i =0; i < size; i++) {
            id[i] = i;
        }
    }
    @Override
    public int getSize() {
        return id.length;
    }

    // 查找元素p对应的集合编号
    private int find(int p) {
        assert p >=0 && p < id.length;
        return id[p];
    }

    @Override
    public boolean isConnected(int p, int q) {
        assert p >=0 && p < id.length;
        assert q >=0 && q < id.length;
        return id[p] == id[q];
    }

    // 合并集合
    @Override
    public void unionElements(int p, int q) {
        assert p >=0 && p < id.length;
        assert q >=0 && q < id.length;
        int pId = find(p);
        int qId = find(q);
        if (pId == qId) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == qId) {
                id[i] = pId;
            }
        }
    }
}
