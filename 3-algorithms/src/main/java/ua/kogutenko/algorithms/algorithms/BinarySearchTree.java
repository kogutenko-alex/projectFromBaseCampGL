package ua.kogutenko.algorithms.algorithms;

public class BinarySearchTree<T extends Comparable<T>> {
    /* Class containing left
           and right child of current node
         * and key value*/
    class Node {
        T key;
        Node left, right;

        public Node(T item)
        {
            key = item;
            left = right = null;
        }
    }

    // Root of BST
    Node root;

    // Constructor
    public BinarySearchTree() {
        root = null;
    }

    // This method mainly calls insertRec()
    public void insert(T key) {
        root = insertRec(root, key);
    }

    /* A recursive function to
       insert a new key in BST */
    public Node insertRec(Node root, T key) {

        /* If the tree is empty,
           return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        int if1 = key.compareTo(root.key);
        int if2 = root.key.compareTo(key);
        if (if1 > 0)
            root.left = insertRec(root.left, key);
        else if (if2 > 0)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }

    // This method mainly calls InorderRec()
    public void inorder() {
        inorderRec(root);
    }

    // A utility function to
    // do inorder traversal of BST
    public void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }
}
