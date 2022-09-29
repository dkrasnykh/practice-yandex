package training_1_0.hw8.second_maximum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class Node {
    int key;
    Node p;
    Node right;
    Node left;

    public Node(int key) {
        this.key = key;
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

    static Node maximun(Node x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    static Node predecessor(Node x) {
        if (x.left != null) {
            return maximun(x.left);
        }
        Node y = x.p;
        while (y != null && x == y.left) {
            x = y;
            y = y.p;
        }
        return y;
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

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
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
            Node maximum = maximun(t);
            writer.println(predecessor(maximum).key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
