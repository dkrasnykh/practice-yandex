package training_1_0.hw8.genealogy_level_counting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Node {
    String name;
    Node parent;
    int level;

    static void buildTree(Node root, Map<String, List<String>> parentChildren, List<Node> all, int level) {
        root.level = level;
        List<String> children = parentChildren.get(root.name);
        if (children != null) {
            level++;
            Node c;
            for (String s : children) {
                c = new Node();
                c.name = s;
                c.parent = root;
                buildTree(c, parentChildren, all, level);
                all.add(c);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            Map<String, List<String>> parentChildren = new HashMap<>();
            Map<String, String> childParent = new HashMap<>();
            String line;
            int space;
            String key;
            String value;
            List<String> val;
            for (int i = 0; i < n - 1; i++) {
                line = reader.readLine();
                space = line.indexOf(" ");
                key = line.substring(0, space);
                value = line.substring(space + 1);

                childParent.put(key, value);

                if (parentChildren.containsKey(value)) {
                    val = parentChildren.get(value);
                    val.add(key);
                    parentChildren.put(value, val);
                } else {
                    val = new ArrayList<>();
                    val.add(key);
                    parentChildren.put(value, val);
                }
            }
            String root = null;
            for (Map.Entry entry : parentChildren.entrySet()) {
                if (!childParent.containsKey(entry.getKey())) {
                    root = (String) entry.getKey();
                    break;
                }
            }
            Node nodeRoot = new Node();
            nodeRoot.name = root;
            List<Node> all = new ArrayList<>();
            all.add(nodeRoot);
            buildTree(nodeRoot, parentChildren, all, 0);
            Collections.sort(all, Comparator.comparing(e -> e.name));
            for (Node node : all) {
                writer.println(node.name + " " + node.level);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
