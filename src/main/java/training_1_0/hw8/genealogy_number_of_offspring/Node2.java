package training_1_0.hw8.genealogy_number_of_offspring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Node2 {

    String name;
    Node2 parent;
    int count;
    int level;

    static int buildTree(Node2 root, Map<String, List<String>> parentChildren, List<Node2> all, int level) {
        int count = 0;
        root.level=level;
        List<String> children = parentChildren.get(root.name);
        if (children != null) {
            level++;
            Node2 c;
            for (String s : children) {
                c = new Node2();
                c.name = s;
                c.parent = root;
                count++;

                count += buildTree(c, parentChildren, all, level);
                all.add(c);
            }
            root.count = count;
        }
        return count;
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
            Node2 nodeRoot = new Node2();
            nodeRoot.name = root;
            List<Node2> all = new ArrayList<>();
            all.add(nodeRoot);
            buildTree(nodeRoot, parentChildren, all, 0);
            Collections.sort(all, Comparator.comparing(e -> e.name));
            for (Node2 node2 : all) {
                writer.println(node2.name + " " + node2.count+" "+ node2.level);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
