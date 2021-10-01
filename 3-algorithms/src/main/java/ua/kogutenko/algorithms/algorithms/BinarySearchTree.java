package ua.kogutenko.algorithms.algorithms;

public class BinarySearchTree/*<T extends Comparable<T>>*/  {
    private static long size = 0;

    /**
     * A node in a binary tree
     */
    private static class BinaryNode implements Comparable<String> {
        private String item;
        private BinaryNode left;
        private BinaryNode right;

        /**
         * constructor to build a node with no subtrees
         * @param value to be stored by this node
         */
        private BinaryNode(String value) {
            item = value;
            left = null;
            right = null;
        }

        /**
         * constructor to build a node with a specified (perhaps null) subtrees
         * @param value to be stored by this node
         * @param l subtree for this node
         * @param r subtree for this node
         */
        private BinaryNode(String value, BinaryNode l, BinaryNode r) {
            item = value;
            left = l;
            right = r;
        }

        @Override
        public int compareTo(String anotherString) {
            int length1 = this.item.length();
            int length2 = anotherString.length();
            return length1 - length2;
        }
    }

    /**
     * the root of the tree is the only data field needed
     * */
    protected BinaryNode root = null; // null when tree is empty

    /**
     * constructs an empty tree
     */
    public BinarySearchTree() {
        super();
    }

    /**
     * constructs a tree with one element, as given
     * @param	value to be used for the one element in the tree
     */
    public BinarySearchTree(String value) {
        super();
        root = new BinaryNode(value);
    }

    /**
     *  constructs a tree with the given node as root
     * @param	newRoot to be used as the root of the new tree
     */
    public BinarySearchTree(BinaryNode newRoot) {
        super();
        root = newRoot;
    }

    /**
     *  find a value in the tree
     * @param	key identifies the node value desired
     * @return	the node value found, or null if not found
     */
    public String get(String key) {
        BinaryNode node = root;
        while (node != null) {
            if (node.item.equals(key)) {
                return node.item;
            }
            if (node.compareTo(key) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }

    /**
     * insert a value to the tree, replacing an existing value if necessary
     * @param	key to be inserted
     */
    public void insert(String key){
        BinaryNode newNode = new BinaryNode(key);
        if(root == null){
            root = newNode;
            size++;
            return;
        }
        BinaryNode current = root;
        BinaryNode parent = null;
        while(true){
            parent = current;
            if(current.compareTo(key) < 0){
                current = current.left;
                if(current == null){
                    size++;
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if(current == null){
                    size++;
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public boolean isEmpty(){

        return root == null;

    }

    public void deleteTree() {
        root = null;
        size = 0;
    }

    public long size() {
        return size;
    }
}