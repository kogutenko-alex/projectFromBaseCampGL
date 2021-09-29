package ua.kogutenko.algorithms.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Trie {
    // Define the alphabet size (26 characters for `a – z`)
    private static final int CHAR_SIZE = 26;

    private boolean isLeaf;
    private List<Trie> children = null;

    // Constructor
    public Trie() {
        isLeaf = false;
        children = new ArrayList<>(Collections.nCopies(CHAR_SIZE, null));
    }

    // Iterative function to insert a string into a Trie
    public void insert(String key) {
        System.out.println("Inserting \"" + key + "\"");

        // start from the root node
        Trie curr = this;

        // do for each character of the key
        for (char c: key.toCharArray())  {
            // create a new Trie node if the path does not exist
            if (curr.children.get(c - 'a') == null) {
                curr.children.set(c - 'a', new Trie());
            }

            // go to the next node
            curr = curr.children.get(c - 'a');
        }

        // mark the current node as a leaf
        curr.isLeaf = true;
    }

    // Iterative function to search a key in a Trie. It returns true
    // if the key is found in the Trie; otherwise, it returns false
    public boolean search(String key) {
        System.out.print("Searching \"" + key + "\" : ");

        Trie curr = this;

        // do for each character of the key
        for (char c: key.toCharArray()) {
            // go to the next node
            curr = curr.children.get(c - 'a');

            // if the string is invalid (reached end of a path in the Trie)
            if (curr == null) {
                return false;
            }
        }

        // return true if the current node is a leaf node and the
        // end of the string is reached
        return curr.isLeaf;
    }
}
