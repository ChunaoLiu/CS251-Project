/*
 * CS 25100 - Data Structures and Algorithms
 * Summer 2021 Project 3
 * Purdue University
 */

public class LLRBT<Key extends Comparable<Key>, Value> {
    private Node root; //the root of the tree
    private int N;     //the number of nodes in the tree


    public LLRBT() {
        this.root = null;
        this.N = 0;
    }

    // PUBLIC METHODS

    /**
     * insert a new (key, val) into tree or replace value of existing key
     * Remember the rules of LLRBT:
     * The root node is always black in color.
     * Every new node inserted into the tree is red.
     * Every null child of a node is considered black.
     * All red nodes should be left-leaning (i.e. left children.)
     * No path should have two red nodes in a row.
     * No node should have two red children.
     * You will have to make sure that the height for every node is set correctly
     * after every insertion
     *
     * @param key
     * @param val
     */

    public void insert(Key key, Value val) {
        //TODO: Implement insert
        Node newNode = new Node(key, val);
        newNode.height = 0;
        newNode.isRed = true;

        root = insert_helper(newNode);
        N += 1;
        if (root.isRed) root.isRed = false;
    }

    public Node insert_helper(Node newNode) {
        Node temp = root;
        if (temp == null) {
            return newNode;
        }
        if (temp.key.compareTo(newNode.key) >= 0) {
            temp.left = insert_helper(temp.left);
        } else if (temp.key.compareTo(newNode.key) < 0) {
            temp.right = insert_helper(temp.right);
        }
        if (temp.right.isRed && !temp.left.isRed) {
            temp = rotateLeft(temp);
        }

        if (temp.left.isRed && temp.left.left.isRed) {
            temp = rotateRight(temp);
        }

        if (temp.right.isRed && temp.left.isRed) {
            temp.flip();
            temp.left.flip();
            temp.right.flip();
        }

        return temp;
    }



    /**
     * Calculates and returns the black height of the tree
     *
     * @return
     */

    public int blackHeight() {
        //TODO: Implement blackHeight()
        int counter = 1;
        Node temp = root;
        while (temp != null) {
            temp = temp.left;
            if (!temp.isRed) {
                counter += 1;
            }
        }
        counter += 1;
        return counter;
    }

    /**
     * returns the height of the node with the given key
     * returns -1 if the key is not found
     *
     * @param key
     * @return
     */
    public int height(Key key) {
        int counter = 0;
        Node temp = root;
        while (temp != null) {
            if (key.compareTo(temp.key) == 0) {
                return counter;
            }
            if (key.compareTo(temp.key) > 0) {
                temp = temp.right;
                counter += 1;
            } else {
                temp = temp.left;
                counter += 1;
            }
        }
        //TODO: Implement height()
        return -1;
    }


    /**
     * get the value associated with the given key
     *
     * @param key
     * @return the value
     */
    public Value get(Key key) {
        Node check = root;
        while (check != null) {
            if (key.compareTo(check.key) == 0) {
                return check.val;
            } else if (key.compareTo(check.key) < 0) {
                check = check.left;
            } else {
                check = check.right;
            }
        }
        return null;
    }


    /**
     * return true if the tree
     * is empty and false otherwise
     *
     * @return
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * return the number of nodes in
     * the tree
     *
     * @return
     */
    public int size() {
        return N;
    }

    /**
     * return the height of the tree
     *
     * @return
     */
    public int height() {
        return height(root);
    }


    /**
     * Swap the color of two nodes
     *
     * @param x
     * @param y
     */
    private void swapColors(Node x, Node y) {
        if (x.isRed == y.isRed)
            return;

        boolean temp = x.isRed;
        x.isRed = y.isRed;
        y.isRed = temp;
    }


    /**
     * rotate a link to the right
     *
     * @param x
     * @return
     */
    private Node rotateRight(Node x) {
        Node temp = x.left;
        x.left = temp.right;
        temp.right = x;
        swapColors(x, temp);
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.left), x.height) + 1;
        return temp;
    }

    /**
     * rotate a link to the left
     *
     * @param x
     * @return
     */
    private Node rotateLeft(Node x) {
        Node temp = x.right;
        x.right = temp.left;
        temp.left = x;
        swapColors(x, temp);
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        temp.height = Math.max(height(temp.right), x.height) + 1;
        return temp;
    }

    /**
     * performs color flip
     *
     * @param x
     * @return
     */
    private Node colorFlip(Node x) {
        if (x.left == null || x.right == null)
            return x;

        if (x.left.isRed == x.right.isRed) {
            x.left.flip();
            x.right.flip();
            x.flip();
        }

        return x;
    }


    /**
     * returns the height of a node
     * or -1 if the ndoe is null
     *
     * @param x
     * @return
     */
    private int height(Node x) {
        if (x == null)
            return -1;

        return x.height;
    }

    /**
     * returns the string representation of the tree
     * level by level
     *
     * @return
     */
    @Override
    public String toString() {
        String ret = "Level 0: ";
        Pair x = null;
        Queue<Pair> queue = new Queue<Pair>(N);
        int level = 0;
        queue.enqueue(new Pair(root, level));

        while (!queue.isEmpty()) {
            x = queue.dequeue();
            Node n = x.node;

            if (x.depth > level) {
                level++;
                ret += "\nLevel " + level + ": ";
            }

            if (n != null) {
                ret += "|" + n.toString() + "|";
                queue.enqueue(new Pair(n.left, x.depth + 1));
                queue.enqueue(new Pair(n.right, x.depth + 1));
            } else
                ret += "|null|";
        }

        ret += "\n";
        return ret;
    }

    /**
     * The NODE Class
     */
    public class Node {
        Key key;
        Value val;
        Node left, right;
        int height;
        boolean isRed;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
            this.isRed = true;
        }

        public String toString() {
            return "(" + key + ", " + val + "): "
                    + height + "; " + (this.isRed ? "R" : "B");
        }

        public void flip() {
            this.isRed = !this.isRed;
        }
    }

    /**
     * The PAIR Class
     */
    public class Pair {
        Node node;
        int depth;

        public Pair(Node node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }
}
