package ua.kogutenko.algorithms.algorithms;

public class Trie {
    static final int ALPHABET_SIZE = 27;

    public class TrieNode {
        public final char apostrophe = '\'';
        public char c;
        public  boolean isWord;
        public TrieNode[] children;

        public TrieNode (char c) {
            this.c = c;
            isWord = false;
            children = new TrieNode[ALPHABET_SIZE];
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode('\0');
    }

    public void insert(String key) {
        TrieNode curr = root;
        for (int i = 0; i < key.length(); i++){
            char c = Character.toLowerCase(key.charAt(i));
            if(c == '\'') continue;
            int index = c - 'a';
            if(curr.children[index] == null) {
                curr.children[index] = new TrieNode(c);
            }
            curr = curr.children[index];
        }
        curr.isWord = true;
    }

    public boolean search(String key) {
        TrieNode node = getNode(key);
        return node != null && node.isWord;
    }

    public boolean startWith(String prefix) {
        return getNode(prefix) != null;
    }

    private TrieNode getNode (String word) {
        TrieNode curr = root ;
        for (int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(isLetter(c)) {
                if(curr.children[c - 'a'] == null) return null;
                curr = curr.children[c - 'a'];
            }
        }
        return  curr;
    }

    private boolean isLetter (char c) {
        if('a' <= c && 'z' >= c)
            return true;
        return false;
    }
}