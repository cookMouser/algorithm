package improve.graph.weight;

public class Main {

    // 测试通过文件读取图的信息
    public static void main(String[] args) {

        // 使用两种图的存储方式读取testG1.txt文件
        String filename = "testG81.txt";
        SparseWeightedGraph<Double> g1 = new SparseWeightedGraph<>(8, false);
        ReadWeightedGraph readGraph1 = new ReadWeightedGraph(g1, filename);
        System.out.println("test G1 in Sparse Weighted Graph:");
        g1.show();

        System.out.println();

        DenseWeightedGraph<Double> g2 = new DenseWeightedGraph<>(8, false);
        ReadWeightedGraph readGraph2 = new ReadWeightedGraph(g2 , filename );
        System.out.println("test G1 in Dense Graph:");
        g2.show();

        System.out.println();
    }
}
