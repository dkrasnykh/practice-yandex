package intensive.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int key;
    List<Node> c;
    Node p;

    int h;

    public Node(int key) {
        this.key = key;
    }
}

public class D {

    static int buildTree(Node node, Map<Integer, List<Integer>> relation) {
        List<Integer> ch = relation.get(node.key);
        int cnt = 1;
        if (ch == null) {
            node.h = 1;
            return 1;
        }
        node.c = new ArrayList<>();
        for (Integer i : ch) {
            Node n = new Node(i);
            n.p = node;
            node.c.add(n);
            cnt += buildTree(n, relation);
        }
        node.h=cnt;
        return cnt;
    }

    static void printTree(Node node){
        System.out.print(node.h+" ");
        if(node.c!=null){
            for(Node n: node.c){
                printTree(n);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int v = Integer.parseInt(reader.readLine());
            String[] line;
            int a, b;
            Map<Integer, List<Integer>> map = new HashMap<>();
            Set<Integer> parent = new HashSet<>();
            parent.add(1);
            for (int i = 0; i < v - 1; i++) {
                line = reader.readLine().trim().split(" ");
                a = Integer.parseInt(line[0]);
                b = Integer.parseInt(line[1]);

                if (parent.contains(a)) {
                    parent.add(b);
                    List<Integer> t = map.get(a) == null ? new ArrayList<>() : map.get(a);
                    t.add(b);
                    map.put(a, t);
                } else if (parent.contains(b)) {
                    parent.add(a);
                    List<Integer> t = map.get(b) == null ? new ArrayList<>() : map.get(b);
                    t.add(a);
                    map.put(b, t);
                }
            }
            Node root = new Node(1);
            buildTree(root, map);
            printTree(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
