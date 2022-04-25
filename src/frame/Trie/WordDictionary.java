package frame.Trie;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * lc：208
 */
public class WordDictionary {

    private class Node{
        public boolean isWord; //当前节点是否已表示一个单词
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node root;
    private int size;

    public WordDictionary() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        if (!cur.isWord) { // 两种情况：新增的node；node已存在但原来不是word
            cur.isWord = true;
            size++;
        }
    }

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(Node node, String word, int index) {
        if (index == word.length()) {
            return node.isWord;
        }
        char c = word.charAt(index);
        if (c != '.') {
            if (!node.next.containsKey(c)) {
                return false;
            }
            return match(node.next.get(c), word, index + 1);
        } else {
            Map<Character, Node> next = node.next;
            if (next.isEmpty()) {
                return false;
            } else {
                for (Character character : node.next.keySet()) {
                    if (match(node.next.get(character), word, index + 1)) {
                     return true;
                    }
                }
            }
        }
        return false;
    }
}
