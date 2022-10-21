package training_1_0.hw7.security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class Node {
    private Pair key;
    private int height = 1;
    private Node left;
    private Node right;
    private int max;
    public Node(Pair key) {
        this.key = key;
        this.max = key.b;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Pair getKey() {
        return key;
    }

    public void setKey(Pair key) {
        this.key = key;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}

class AVLTree {
    private Node root;
    private int size = 0;

    public AVLTree insert(Pair key) {
        this.root = insert(key, root);
        return this;
    }

    private Node insert(Pair key, Node node) {
        if (node == null) {
            this.size++;
            return new Node(key);
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(insert(key, node.getLeft()));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(insert(key, node.getRight()));
        } else {
            return node;
        }
        updateHeight(node);
        updateMax(node);
        return applyRotation(node);
    }

    public int getSize() {
        return this.size;
    }
    private void updateMax(Node node) {
        int maxNode = Math.max(maxEnd(node.getLeft()), maxEnd(node.getRight()));
        int newMax = Math.max(node.getKey().b, maxNode);
        node.setMax(newMax);
    }

    private void updateHeight(Node node) {
        int maxHeight = Math.max(height(node.getLeft()), height(node.getRight()));
        node.setHeight(maxHeight + 1);
    }

    private int maxEnd(Node node) {
        return node != null ? node.getMax() : 0;
    }

    private int height(Node node) {
        return node != null ? node.getHeight() : 0;
    }

    private Node applyRotation(Node node) {
        int balance = balance(node);
        if (balance > 1) {
            if (balance(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (balance < -1) {
            if (balance(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.getRight();
        Node centerNode = rightNode.getLeft();
        rightNode.setLeft(node);
        node.setRight(centerNode);
        updateHeight(node);
        updateHeight(rightNode);
        updateMax(node);
        updateMax(rightNode);
        return rightNode;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.getLeft();
        Node centerNode = leftNode.getRight();
        leftNode.setRight(node);
        node.setLeft(centerNode);
        updateHeight(node);
        updateHeight(leftNode);
        updateMax(node);
        updateMax(leftNode);
        return leftNode;
    }
    private int balance(Node node) {
        return node != null ? height(node.getLeft()) - height(node.getRight()) : 0;
    }

    public Node intervalSearch(Pair pair) {
        Node x = this.root;
        while (x != null) {
            if(x.getKey().a >= pair.a && x.getKey().b <= pair.b){
                break;
            }
            if(pair.a >= x.getKey().a && pair.b <= x.getKey().b){
                break;
            }
            if (x.getLeft() != null && x.getLeft().getMax() >= pair.a) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        return x;
    }

    public Node findIncludedBBorder(Pair pair) {
        Node x = this.root;
        Node y = null;
        int max = 0;
        while (x != null) {
            if (x.getKey().b > pair.a && x.getKey().b < pair.b && x.getKey().b > max) {
                max = x.getKey().b;
                y = x;
            }
            if (x.getLeft() != null && x.getLeft().getMax() >= pair.a) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }

        }
        return y;
    }

    public Node findIncludedABorder(Pair pair) {
        Node x = this.root;
        Node y = null;
        int min = 10001;
        while (x != null) {
            if (x.getKey().a > pair.a && x.getKey().a < pair.b && x.getKey().a < min) {
                min = x.getKey().a;
                y = x;
            }
            if (x.getLeft() != null && x.getLeft().getKey().a > pair.a) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }

        }
        return y;
    }

    public void fillList(List<Pair> result) {
        fillList(root, result);
    }

    private void fillList(Node node, List<Pair> result) {
        if (node != null) {
            fillList(node.getLeft(), result);
            result.add(node.getKey());
            fillList(node.getRight(), result);
        }
    }
}

class Pair implements Comparable {
    int a;
    int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return a == pair.a && b == pair.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public int compareTo(Object o) {
        Pair other = (Pair) o;
        return (this.a == other.a) ? this.b - other.b : this.a - other.a;
    }
}

public class Security {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int k = Integer.parseInt(reader.readLine());
            String[] line;
            AVLTree tree1;
            List<String> result = new ArrayList<>();
            Map<String, String> answers = new HashMap<>();
            String str;
            String res;
            for (int i = 0; i < k; i++) {
                tree1 = new AVLTree();
                str = reader.readLine();
                res = answers.get(str);
                if(res!=null){
                    result.add(res);
                    continue;
                }
                line = str.split(" ");
                int n = Integer.parseInt(line[0]);
                for (int j = 0; j < n; j++) {
                    Pair p = new Pair(Integer.parseInt(line[2 * j + 1]), Integer.parseInt(line[2 * j + 2]));
                    Node x = tree1.intervalSearch(p);
                    if (x != null) {
                        break;
                    }
                    tree1.insert(p);
                }

                if (tree1.getSize() != n) {
                    result.add("Wrong Answer");
                    answers.put(str, "Wrong Answer");
                    continue;
                }

                List<Pair> sortedPairs = new ArrayList<>();
                tree1.fillList(sortedPairs);
                for (Pair p : sortedPairs) {
                    Node p1 = tree1.findIncludedBBorder(p);
                    Node p2 = tree1.findIncludedABorder(p);
                    if (p1 != null && p2 != null && p1.getKey().b > p2.getKey().a) {
                        result.add("Wrong Answer");
                        answers.put(str, "Wrong Answer");
                        break;
                    }
                }
                if (i + 1 == result.size()) {
                    continue;
                }
                if (sortedPairs.get(0).a != 0 || sortedPairs.get(sortedPairs.size() - 1).b != 10000) {
                    result.add("Wrong Answer");
                    answers.put(str, "Wrong Answer");
                    continue;
                }
                for (int j = 1; j < sortedPairs.size(); j++) {
                    if (sortedPairs.get(j).a > sortedPairs.get(j - 1).b) {
                        result.add("Wrong Answer");
                        answers.put(str, "Wrong Answer");
                        break;
                    }
                }
                if (i + 1 > result.size()) {
                    result.add("Accepted");
                    answers.put(str, "Accepted");
                }
            }
            for (String ans : result) {
                writer.println(ans);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
