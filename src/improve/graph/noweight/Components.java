package improve.graph.noweight;

/**
 * 求解连通分量
 */
public class Components {
    private Graph G;
    private boolean[] visited; // 记录每个节点是否被遍历过
    private int ccount;
    private int id[];  // id数组，用于判断两个元素是否相连。如果相连则数组值相同（值为每一轮dfs的ccount值，每个元素只会遍历一次，且不同的子图其ccount值不同）

    public Components(Graph graph) {
        this.G = graph;
        ccount = 0;
        visited = new boolean[G.V()];
        id = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            visited[i] = false;
            id[i] = -1; //取一个ccount不可能取的值
        }


        for (int i = 0; i < G.V(); i++) {
            // 在相连的一个子图中，如果dfs一个元素，子图所有元素都会是visited。即每执行一轮dfs则代表有一个子图
            if (!visited[i]) {
                dfs(i);
                ccount++;
            }
        }

    }

    // 深度优先遍历
    private void dfs(int v) {
        visited[v] = true;
        id[v] = ccount;
        for (int i : G.adj(v)) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public int count() {
        return ccount;
    }

    public boolean isConnected(int v, int w) {
        assert(v >= 0 && v < G.V());
        assert(w >= 0 && w < G.V());
        return id[v] == id[w];
    }
}
