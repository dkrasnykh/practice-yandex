package training_1_0.hw8.genealogy_number_of_offspring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Node1 {
    String name;
    Node p;
    int countDes;
    public Node1(String name) {
        this.name = name;
    }
    static int buildTree(Node1 parent, String[] child, String[] parents, List<Node1> allElements) {
        Node1 c;
        int count = 0;
        for (int i = 0; i < parents.length; i++) {
            if (parents[i].equals(parent.name)) {
                count++;
                c = new Node1(child[i]);
                allElements.add(c);
                count += buildTree(c, child, parents, allElements);
            }
        }
        parent.countDes = count;
        return count;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String[] parent = new String[n - 1];
            String[] child = new String[n - 1];
            String line;
            int space;
            Set<String> childrenSet = new HashSet<>();
            Set<String> parentSet = new HashSet<>();

            for (int i = 0; i < n - 1; i++) {
                line = reader.readLine();
                space = line.indexOf(" ");
                child[i] = line.substring(0, space);
                childrenSet.add(child[i]);
                parent[i] = line.substring(space + 1);
                parentSet.add(parent[i]);
            }

            parentSet.removeAll(childrenSet);

            List<Node1> allElements = new ArrayList<>();

            Node1 root = new Node1(parentSet.stream().findFirst().get());
            allElements.add(root);

            buildTree(root, child, parent, allElements);

            Collections.sort(allElements, Comparator.comparing(e -> e.name));
            for (Node1 node : allElements) {
                writer.println(node.name + " " + node.countDes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
