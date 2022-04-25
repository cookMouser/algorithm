package frame.Trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * lc：677
 */
public class MapSum {

    private class Node{
        public int value; //此题中不需要关心node是不是添加进去的节点，只需要关心value值，如果不是添加的节点那么value就为0，就算sum时做了加法也无所谓
        public TreeMap<Character, Node> next;

        public Node(int value) {
            this.next = new TreeMap<>();
            this.value = value;
        }

        public Node() {
            this(0);
        }
    }

    private Node root;

    public MapSum() {
        root = new Node();
    }

    public void insert(String key, int value) {
        Node cur = root;
        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.value = value;
    }

    public int sum(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) {
                return 0;
            }
            cur = cur.next.get(c);
        }
        return sum(cur);

    }

    // 计算当前节点树的和
    private int sum(Node node) {
        int sum = node.value;
        Map<Character, Node> map = node.next;
        if (map.size() != 0) {
            for (Character character : map.keySet()) {
                sum += map.get(character).value;
            }
        }
        return sum;
    }
}
