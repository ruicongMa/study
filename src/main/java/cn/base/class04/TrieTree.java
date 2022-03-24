package cn.base.class04;

import java.util.HashMap;

/**
 * @author Mark
 * @date 2021/7/1 下午5:23
 */
public class TrieTree {

    public static class Node {
        int pass;
        int end;
        Node[] nexts;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.nexts = new Node[26];
        }
    }

    public static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        public void insert(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            Node node = root;
            node.pass++;
            char[] str = word.toCharArray();
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }

        public int search(String word) {
            if (word == null || word.length() == 0) {
                return 0;
            }
            char[] str = word.toCharArray();
            Node node = root;
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        public int prefixNumber(String pre) {
            if (pre == null || pre.length() == 0) {
                return 0;
            }
            char[] str = pre.toCharArray();
            Node node = root;
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] str = word.toCharArray();
                Node node = root;
                node.pass--;
                for (int i = 0; i < str.length; i++) {
                    int path = str[i] - 'a';
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;
                    }
                    node = node.nexts[path];
                }
                node.end--;
            }
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root;

        public Trie2() {
            root = new Node2();
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            node.pass++;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    node.nexts.put(index, new Node2());
                }
                node = node.nexts.get(index);
                node.pass++;
            }
            node.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node2 node = root;
                node.pass--;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = (int) chs[i];
                    if (--node.nexts.get(index).pass == 0) {
                        node.nexts.remove(index);
                        return;
                    }
                    node = node.nexts.get(index);
                }
                node.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node2 node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = (int) chs[i];
                if (!node.nexts.containsKey(index)) {
                    return 0;
                }
                node = node.nexts.get(index);
            }
            return node.pass;
        }
    }

    public static void main(String[] args) {

        Trie2 trie2 = new Trie2();
        trie2.insert("aba");
        trie2.insert("a");
        trie2.insert("abc");
        System.out.println();


        // Trie trie = new Trie();
        // trie.insert("ab");
        // trie.insert("abc");
        // trie.insert("abcd");
        // trie.insert("ab");
        // System.out.println(trie.search("a"));
        // System.out.println(trie.search("ab"));
        // System.out.println(trie.search("abc"));
        // System.out.println("==========");
        // System.out.println(trie.prefixNumber("a"));
        // System.out.println(trie.prefixNumber("ab"));
        // System.out.println(trie.prefixNumber("abc"));
        // System.out.println(trie.prefixNumber("abcd"));
        // System.out.println(trie.prefixNumber("e"));
        // System.out.println("==========");
        // trie.delete("ab");
        // trie.delete("ab");
        // System.out.println(trie.search("ab"));
        // trie.delete("abc");
        // System.out.println(trie.search("abc"));
        // trie.delete("abcd");
        // System.out.println(trie.search("abcd"));
    }

}
