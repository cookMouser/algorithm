package frame.UnionFind;

import java.util.Random;

/**
 * parent数组
 * 基于rank 的优化
 */
public class UnionFind4 implements UF {

    private int[] parent;
    private int[] rank; //记录每个树的高度，索引为root的索引

    public UnionFind4(int size) {
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
            rank[pRoot] = 0;
        } else if (rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
            rank[qRoot] = 0;
        } else { //相等时
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
            rank[qRoot] = 0;
        }
    }

    public static void main(String[] args) {
        int size = 100000;
        int m = 100000;

        UF uf1 = new UnionFind1(size);
        System.out.println(testUF(uf1, m));
        UF uf2 = new UnionFind2(size);
        System.out.println(testUF(uf2, m));
        UF uf3 = new UnionFind3(size);
        System.out.println(testUF(uf3, m));
        UF uf4 = new UnionFind4(size);
        System.out.println(testUF(uf4, m));
    }

    public static double testUF(UF uf, int m) {
        int size = uf.getSize();
        Random random = new Random();

        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a , b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a , b);
        }

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
