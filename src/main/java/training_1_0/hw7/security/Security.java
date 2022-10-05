package training_1_0.hw7.security;

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
    private int max;

    public Node() {
    }

    public Node(Pair key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
        this.max = key.b;
    }

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
            return node; //не добавляет копию
        }
        updateHeight(node);
        updateMax(node);
        return applyRotation(node);
    }

    public int getSize() {
        return this.size;
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
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            node.setKey(max(node.getLeft()));
            node.setLeft(delete(node.getKey(), node.getLeft()));
        }
        updateHeight(node);
        updateMax(node);
        return applyRotation(node);
    }

    private Pair max(Node node) {
        if (node.getRight() != null) {
            return max(node.getRight());
        }
        return node.getKey();
    }

    private Pair min(Node node) {
        if (node.getLeft() != null) {
            return min(node.getLeft());
        }
        return node.getKey();
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

    public void treverse() {
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println("a: " + node.getKey().a + "; b: " + node.getKey().b + "; max: " + node.getMax());
            traverseInOrder(node.getRight());
        }
    }

    public Node intervalSearch(Pair pair) {
        Node x = this.root;
        while (x != null && (x.getKey().a > pair.b || x.getKey().b < pair.a)) {
            if (x.getLeft() != null && x.getLeft().getMax() >= pair.a) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        //return x;
        return x;
    }

    public Node intervalSearch1(Pair pair) {
        Node x = this.root;
        while (x != null && (x.getKey().a > pair.b || x.getKey().b < pair.a || x.getKey().a <= pair.a && x.getKey().b <= pair.b)) {
            if (x.getLeft() != null && x.getLeft().getMax() >= pair.a) {
                x = x.getLeft();
            } else {
                x = x.getRight();
            }
        }
        //return x;
        return x;
    }
    /*
    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println("a: " + node.getKey().a + "; b: " + node.getKey().b + "; max: " + node.getMax());
            traverseInOrder(node.getRight());
        }
    }
    */
    public void fillList(List<Pair> result){
        fillList(root, result);
    }
    private void fillList(Node node, List<Pair> result){
        if(node!=null){
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

class Event implements Comparable {
    static class Test {
        int start;
        int end;
    }

    private int position;
    private int type;
    private int id;
    private boolean applicable;
    private int other;

    public Event(int id, int position, int other, int type) {
        this.position = position;
        this.type = type;
        this.other = other;
    }

    public int getPosition() {
        return position;
    }

    public int getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return position == event.position && type == event.type && id == event.id && other == event.other;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, type, id, other);
    }

    @Override
    public int compareTo(Object o) {
        Event other = (Event) o;
        return (this.position == other.position) ? this.type - other.type : this.position - other.position;
    }
}

/*
1 - приход
-1 - выход
 */
public class Security {
    //static int binsearch(int l, int r, ){}
    /*
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int k = Integer.parseInt(reader.readLine());
            //k - количество охранников
            List<Event> events = new ArrayList<>();
            int[] scanline = new int[10000];
            String[] line;
            for (int i = 0; i < k; i++) {
                line = reader.readLine().split(" ");
                int n = Integer.parseInt(line[0]);
                for (int j = 0; j < n; j++) {
                    int start = Integer.parseInt(line[2 * j + 1]);
                    int end = Integer.parseInt(line[2 * j + 2]);
                    for (int p = start + 1; p <= end; p++) {
                        scanline[p]++;
                    }
                    events.add(new Event(j + 1, Integer.parseInt(line[2 * j + 1]), Integer.parseInt(line[2 * j + 2]), 1));
                    events.add(new Event(j + 1, Integer.parseInt(line[2 * j + 2]), Integer.parseInt(line[2 * j + 1]), -1));
                }

            }
            HashSet<Event> open = new HashSet<>();
            Collections.sort(events);




            for (Event e : events) {
                if (e.getType() == 1) {
                    open.add(e);
                } else {

                }
            }


            open.add(events.get(0));
            int cnt = 1;
            for (int i = 1; i < events.size(); i++) {
                if (events.get(i).getType() == 1) {
                    //искать в списке open event, который начался до текущего и для которого работа ещё не завершена
                    if (cnt == 1) {
                        Event e = open.iterator().next();
                        if (e.getPosition() < events.get(i).getPosition()) {

                        }
                    }
                } else {

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        tree.insert(new Pair(0, 3)).insert(new Pair(6, 10)).insert(new Pair(5, 8)).insert(new Pair(15, 23)).insert(new Pair(8, 9))
                .insert(new Pair(16, 21)).insert(new Pair(19, 20)).insert(new Pair(26, 26)).insert(new Pair(25, 30)).insert(new Pair(17, 19));
        //tree.treverse();

        tree.insert(new Pair(17, 19));
        //tree.treverse();
        //System.out.println(tree.getSize());
        //Node x = tree.intervalSearch(new Pair(19, 20));
        //int test = 5;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int k = Integer.parseInt(reader.readLine());
            String[] line;
            AVLTree tree1;
            List<String> result = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                tree1 = new AVLTree();
                line = reader.readLine().split(" ");
                List<Pair> pairs = new ArrayList<>();
                int n = Integer.parseInt(line[0]);

                for (int j = 0; j < n; j++) {
                    Pair p = new Pair(Integer.parseInt(line[2 * j + 1]), Integer.parseInt(line[2 * j + 2]));
                    pairs.add(p);
                    //не включать отрезки, которые включены друг в друга
                    //если добавляем отрезок, а в дереве уже существует такой который входит в новый отрезок
                    //если добавляем отрезок, а в дереве уже существует такой который включает новый отрезок
                    Node x = tree1.intervalSearch1(p);
                    if (x != null) {
                        //result.add("Wrong Answer");
                        break;
                    }
                    tree1.insert(p);
                }

                if (tree1.getSize() != pairs.size()) {
                    result.add("Wrong Answer");
                    continue;
                }

                List<Pair> sortedPairs = new ArrayList<>();
                tree1.fillList(sortedPairs);
                //for(Pair p: tr){}
                int test = 5;


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
