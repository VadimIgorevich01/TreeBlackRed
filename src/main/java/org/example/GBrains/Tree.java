package org.example.GBrains;

public class Tree {
    Node root;

    class Node {
        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color {
        BLACK, RED;
    }

    public void insert(int value) {
        if (root != null) {
            insert(root, value);
            root = balance(root);
        } else {
            root = new Node();
            root.value = value;
        }
        root.color = Color.BLACK;
    }

    private void insert(Node node, int value) {
        if (node.value != value) {
            if (node.value < value) {
                if (node.right == null) {
                    node.right = new Node();
                    node.right.value = value;
                    node.right.color = Color.RED;
                } else {
                    insert(node.right, value);
                    node.right = balance(node.right);
                }
            } else {
                if (node.left == null) {
                    node.left = new Node();
                    node.left.value = value;
                    node.right.color = Color.RED;
                } else {
                    insert(node.left, value);
                    node.left = balance(node.left);
                }
            }
        }
    }

    private Node leftRotate(Node node) {
        Node cur = node.right;
        node.right = cur.left;
        cur.left = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }

    private Node rightRotate(Node node) {
        Node cur = node.left;
        node.left = cur.right;
        cur.right = node;
        cur.color = node.color;
        node.color = Color.RED;
        return cur;
    }
    private void swapColors(Node node) {
        node.color = (node.color == Color.RED ? Color.BLACK : Color.RED);
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;

    }

    private Node balance(Node node) {
        boolean flag = true;
        Node cur = node;
        do {
            flag = false;

            if (cur.right != null && cur.right.color == Color.RED && (cur.left == null || cur.left.color == Color.BLACK)) {
                cur = leftRotate(cur);
                flag = true;
            }

            if (cur.left != null && cur.left.color == Color.RED && cur.left.left != null && cur.left.left.color == Color.RED) {
                cur = rightRotate(cur);
                flag = true;
            }

            if (cur.left != null && cur.left.color == Color.RED && cur.right != null && cur.right.color == Color.RED) {
                swapColors(cur);
                flag = true;
            }
        } while(flag);
        return cur;
    }
}