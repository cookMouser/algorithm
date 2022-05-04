package frame.SegmentTree;

public class SegmentTree<E> {

    private E[] data;
    private E[] tree;
    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.data = (E[])new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        this.tree = (E[])new Object[arr.length * 4];
        this.merge = merge;
        // 线段树不允许动态增加删除元素
        buildSegmentTree(0, 0, data.length - 1);
    }

    public int size() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is illegal");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 递归的逻辑：
     * 现在已经拿到了原始数组，需要将原始数组的数据填充到新数组中，为了让节点之间index满足固定关系，我们将树填充为一个满二叉树
     * 递归还是从root开始，每一次递归已知的信息有当前节点的index，涵盖范围[left, right]，而每个节点具体填什么值，需要确定子节点的值
     * 然后做merge操作，当执行到最后的时候left==right，就能从原数组提取到值arr[left]或者arr[right]，然后递归反向回去就填充好了整个树
     * @param treeIndex
     */
    // 该方法left、right参数是为了找到叶子节点，确定叶子节点的值。treeIndex是为了定位线段树节点对应的数组索引，然后塞入值
    private void buildSegmentTree(int treeIndex, int left, int right){
        //递归终止条件
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }
        int treeLeftIndex = leftChild(treeIndex);
        int treeRightIndex = rightChild(treeIndex);
        int mid = left + (right - left) / 2;
        buildSegmentTree(treeLeftIndex, left, mid);
        buildSegmentTree(treeRightIndex, mid + 1, right);
        // merge
        tree[treeIndex] = merge.merge(tree[treeLeftIndex], tree[treeRightIndex]);

    }

    public void set(int index, E e) {
        if (index < 0 || index > data.length - 1) {
            throw new IllegalArgumentException("index is invalid");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // treeIndex用于更改对应元素值，同时用于找到左右子节点
    // left、right用于和index比较确定当前要更改的值，同时确定子节点的区间判断向下递归的方向
    private void set(int treeIndex, int left, int right, int index, E e) {
        if (left == right && left == index) {
            tree[treeIndex] = e;
            return;
        }
        int treeLeftIndex = leftChild(treeIndex);
        int treeRightIndex = rightChild(treeIndex);

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            set(treeLeftIndex, left, mid, index, e);
        } else {
            set(treeRightIndex, mid + 1, right, index, e);
        }
        // 注意刷新所有父节点的值
        tree[treeIndex] = merge.merge(tree[treeLeftIndex], tree[treeRightIndex]);
    }

    public E query(int searchLeft, int searchRight) {
        if (searchLeft < 0 || searchLeft > data.length - 1
                || searchRight < 0 || searchRight > data.length - 1 || searchLeft > searchRight) {
            throw new IllegalArgumentException("index is invalid");
        }
        return query(0, 0, data.length - 1, searchLeft, searchRight);
    }

    // treeIndex用于查找子节点
    // left、right用于递归方向
    private E query(int treeIndex, int left, int right, int searchLeft, int searchRight) {

        //递归终止条件
        if (left == searchLeft && right == searchRight) {
            return tree[treeIndex];
        }
        int treeLeftIndex = leftChild(treeIndex);
        int treeRightIndex = rightChild(treeIndex);

        int mid = left + (right - left) / 2;
        if (searchRight <= mid) { // 区间都在左边
            return query(treeLeftIndex, left, mid, searchLeft, searchRight);
        } else if (searchLeft > mid) { // 区间都在右边
            return query(treeRightIndex, mid + 1, right, searchLeft, searchRight);
        } else {
            E leftRes = query(treeLeftIndex, left, mid, searchLeft, mid);
            E rightRes = query(treeRightIndex, mid + 1, right, mid + 1, searchRight);
            return merge.merge(leftRes, rightRes);
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] == null) {
                sb.append("null");
            } else {
                sb.append(tree[i]);
            }
            if (i != tree.length - 1) {
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(1, 3));
    }
}
