package training_1_0.hw8.tree_height;

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

    public Node() {
    }

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

    static Node root(Node x) {
        while (x != null && x.p != null) {
            x = x.p;
        }
        return x;
    }

    static int insert(Node t, Node z) {
        Node y = null;
        Node x = root(t);
        int height = 1;
        while (x != null) {
            height++;
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
        return height;
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
            if (arr.length == 0) {
                writer.println(0);
                return;
            }
            if (arr.length == 1) {
                writer.println(1);
                return;
            }
            Node t = new Node(arr[0]);
            writer.print("1 ");
            for (int i = 1; i < arr.length; i++) {
                Node n = search(t, arr[i]);
                if (n == null) {
                    writer.print(insert(t, new Node(arr[i])) + " ");

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}