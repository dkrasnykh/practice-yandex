package training_1_0.hw8.traversal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Node {
    int key;
    Node p;
    Node right;
    Node left;

    public Node(int key) {
        this.key = key;
    }

    static void inorderWalk(Node x) {
        if (x != null) {
            inorderWalk(x.left);
            System.out.println(x.key);
            inorderWalk(x.right);
        }
    }

    static Node root(Node x) {
        while (x != null && x.p != null) {
            x = x.p;
        }
        return x;
    }

    static void insert(Node t, Node z) {
        Node y = null;
        Node x = root(t);
        while (x != null) {
            y = x;
            if (z.key < x.key) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        z.p = y;
        if (z.key < y.key) {
            y.left = z;
        } else {
            y.right = z;
        }
    }

    static Node search(Node x, int key) {
        if (x == null || x.key == key) {
            return x;
        }
        if (key < x.key) {
            return search(x.left, key);
        } else {
            return search(x.right, key);
        }
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] line = regex.split(reader.readLine());
            int[] arr = new int[line.length - 1];
            for (int i = 0; i < line.length - 1; i++) {
                arr[i] = Integer.parseInt(line[i]);
            }
            Node t = new Node(arr[0]);
            for (int i = 1; i < arr.length; i++) {
                if (search(t, arr[i]) == null) {
                    insert(t, new Node(arr[i]));
                }
            }
            inorderWalk(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
