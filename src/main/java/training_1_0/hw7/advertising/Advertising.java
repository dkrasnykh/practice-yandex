package training_1_0.hw7.advertising;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class Node {
    private Pair key;
    private int height = 1;
    private Node left;
    private Node right;
    private int cnt = 1;
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

    public int getCnt() {
        return cnt;
    }

    public void incrementCnt() {
        this.cnt++;
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
            node.incrementCnt();
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

    public void delete(Pair key) {
        root = delete(key, root);
    }

    private Node delete(Pair key, Node node) {
        if (node == null) {
            return null;
        }
        if (key.compareTo(node.getKey()) < 0) {
            node.setLeft(delete(key, node.getLeft()));
        } else if (key.compareTo(node.getKey()) > 0) {
            node.setRight(delete(key, node.getRight()));
        } else {
            this.size--;
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setKey(max(node.getLeft()));
            node.setLeft(delete(node.getKey(), node.getLeft()));
        }
        updateMax(node);
        updateHeight(node);
        return applyRotation(node);
    }

    private Pair max(Node node) {
        if (node.getRight() != null) {
            return max(node.getRight());
        }
        return node.getKey();
    }

    public int cntIntervalSearch(int a, int b) {
        int ans = 0;
        Node x = this.root;
        if (x != null && x.getKey().a <= a && b <= x.getKey().b) {
            ans += x.getCnt();
        }
        if (x != null && x.getLeft() != null && x.getLeft().getMax() >= a) {
            x = x.getLeft();
            while (x != null) {
                if (x.getKey().a <= a && b <= x.getKey().b) {
                    ans += x.getCnt();
                }
                if (x.getLeft() != null && x.getLeft().getMax() >= a) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
        }
        if (this.root != null && this.root.getRight() != null && this.root.getRight().getMax() >= a) {
            x = this.root.getRight();
            while (x != null) {
                if (x.getKey().a <= a && b <= x.getKey().b) {
                    ans += x.getCnt();
                }
                if (x.getLeft() != null && x.getLeft().getMax() >= a) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
        }
        return ans;
    }

    public void getListOfIntervalKeys(int a, int b, List<Pair> pairList) {
        Node x = this.root;
        if (x != null && x.getKey().a <= a && b <= x.getKey().b) {
            pairList.add(x.getKey());
        }
        if (x != null && x.getLeft() != null && x.getLeft().getMax() >= a) {
            x = x.getLeft();
            while (x != null) {
                if (x.getKey().a <= a && b <= x.getKey().b) {
                    pairList.add(x.getKey());
                }
                if (x.getLeft() != null && x.getLeft().getMax() >= a) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }
            }
        }
        if (this.root != null && this.root.getRight() != null && this.root.getRight().getMax() >= a) {
            x = this.root.getRight();
            while (x != null) {
                if (x.getKey().a <= a && b <= x.getKey().b) {
                    pairList.add(x.getKey());
                }
                if (x.getLeft() != null && x.getLeft().getMax() >= a) {
                    x = x.getLeft();
                } else {
                    x = x.getRight();
                }

            }
        }
    }

    public void treverse(List<Pair> pairs, int a, int b) {
        traverseInOrder(root, pairs, a, b);
    }

    public void traverseInOrder(Node node, List<Pair> pairs, int a, int b) {
        if (node != null) {
            traverseInOrder(node.getLeft(), pairs, a, b);
            if (node.getKey().a <= a && b <= node.getKey().b) {
                pairs.add(node.getKey());
            }
            traverseInOrder(node.getRight(), pairs, a, b);
        }
    }

    public Node findNode(int a) {
        return findNode(root, a);
    }

    private Node findNode(Node node, int a) {
        while (node != null && node.getKey().a < a) {
            if (a < node.getKey().a) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return node;
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

public class Advertising {

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            AVLTree tree = new AVLTree();
            int start = 1000000001;
            int end = 0;
            String[] line;
            int a;
            int b;
            for (int i = 0; i < n; i++) {
                line = reader.readLine().trim().split(" ");
                a = Integer.parseInt(line[0]);
                b = Integer.parseInt(line[1]);
                start = Math.min(a, start);
                end = Math.max(b, end);
                tree.insert(new Pair(a, b));
            }

            int maxa = 0;
            int maxb = 0;
            int max = 0;
            List<Pair> res;
            List<Pair> maxlist=new ArrayList<>();
            int maxalist = 0;
            int maxblist = 0;
            int maxl = 0;
            for (int i = start; i <= end; i++) {
                a = i;
                b = i + 5;
                res = new ArrayList<>();
                //tree.treverse(res, a, b);
                //tree.traverseInOrder(tree.findNode(a), res, a, b);

                tree.treverse(res, a, b);
                int lsize = res.size();
                if (lsize > maxl) {
                    maxalist = a;
                    maxblist = b;
                    maxl = lsize;
                    maxlist = res;
                }
            }
            if (maxl == 0) {
                writer.println("0 1 6");
                return;
            }
            //List<Pair> removed = new ArrayList<>();
            //tree.getListOfIntervalKeys(maxa, maxb, removed);
            for (Pair p : maxlist) {
                tree.delete(p);
            }
            int maxa1 = 0;
            int maxb1 = 0;
            int max1 = 0;
            List<Pair> res1;
            List<Pair> maxlist1=new ArrayList<>();

            if (start + 5 < maxa) {
                for (int i = start; i < maxa; i++) {
                    a = i;
                    b = i + 5;
                    res1 = new ArrayList<>();
                    //tree.traverseInOrder(tree.findNode(a), res1, a, b);  //tree.cntIntervalSearch(a, b);
                    tree.treverse(res1, a, b);
                    int cnt = res1.size();
                    if (cnt > max1) {
                        maxa1 = a;
                        maxb1 = b;
                        max1 = cnt;
                        maxlist1 = res1;
                    }
                }
            }

            for (int i = maxb; i < end; i++) {
                a = i;
                b = i + 5;
                res1 = new ArrayList<>();
                //tree.traverseInOrder(tree.findNode(a), res1, a, b);  //tree.cntIntervalSearch(a, b);
                tree.treverse(res1, a, b);
                int cnt = res1.size();
                if (cnt > max1) {
                    maxa1 = a;
                    maxb1 = b;
                    max1 = cnt;
                    maxlist1 = res1;
                }
            }
            int border2 = (maxa1 == 0) ? maxblist + 1 : maxa1;
            writer.println(maxl + max1 + " " + Math.min(maxalist, border2) + " " + Math.max(maxalist, border2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
