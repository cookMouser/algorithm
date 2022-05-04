package shuati.base;


import org.omg.CORBA.Object;

public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.data = arr;
        this.tree = (E[])new Object[4 * arr.length];
        this.merge = merge;
        if (data.length > 0) {
            buildSegmentTree(0, 0, data.length - 1);
        }
    }

    public int size() {
        return data.length;
    }

    public E get(int index) {
        assert index >= 0 && index < data.length;
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
        if (left == right) {
            tree[treeIndex] = data[left];
            return;
        }
        int mid = left + (right - left) / 2;
        buildSegmentTree(leftChild(treeIndex), left, mid);
        buildSegmentTree(rightChild(treeIndex), mid + 1, right);
        tree[treeIndex] = merge.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
    }

    public void set(int index, E e) {
        assert index >= 0 && index < data.length;
        // 首先修改原数组
        data[index] = e;

    }

    // treeIndex用于更改对应元素值，同时用于找到左右子节点
    // left、right用于和index比较确定当前要更改的值，同时确定子节点的区间判断向下递归的方向
    private void set(int treeIndex, int left, int right, int index, E e) {
        if (left == right) { // index一定在原数组索引范围内，因此一定能找到值
            tree[treeIndex] = e;
            return;
        }
        int mid = left + (right - left) / 2;
        if (index <= mid) {
            set(leftChild(treeIndex), left, mid, index, e);
        } else {
            set(rightChild(treeIndex), mid + 1, right, index, e);
        }
        tree[treeIndex] = merge.merge(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)]);
    }

    public E query(int searchLeft, int searchRight) {
        assert searchLeft >= 0 && searchLeft < data.length && searchRight >= 0 && searchRight < data.length;
        return query(0, 0, data.length, searchLeft, searchRight);
    }

    // treeIndex用于查找子节点
    // left、right用于递归方向
    private E query(int treeIndex, int left, int right, int searchLeft, int searchRight) {
        if (left == searchLeft && right == searchRight) {
            return tree[treeIndex];
        }
        int mid = left + (right - left) / 2;
        if (searchRight <= mid) {
            return query(leftChild(treeIndex), left, mid, searchLeft, searchRight);
        } else if (searchLeft > mid) {
            return query(rightChild(treeIndex), mid + 1, right, searchLeft, searchRight);
        } else {
            E leftRes = query(leftChild(treeIndex), left, mid, searchLeft, mid);
            E rightRes = query(rightChild(treeIndex), mid + 1, right, mid + 1, searchRight);
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
