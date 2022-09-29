package training_1_0.hw8.genealogy_number_of_offspring;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Node {
    String name;
    Node p;
    int countDes;

    public Node(String name, Node parent) {
        this.name = name;
        this.p = parent;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            String line;
            int space;
            Set<String> parentSet = new HashSet<>();

            Map<String, String> childParent = new HashMap<>();
            String par;
            for (int i = 0; i < n - 1; i++) {
                line = reader.readLine();
                space = line.indexOf(" ");
                par = line.substring(space + 1);
                parentSet.add(par);
                childParent.put(line.substring(0, space), par);
            }

            List<Node> all = new ArrayList<>();
            for(Map.Entry<String, String> entry : childParent.entrySet()){
                if (!parentSet.contains(entry.getKey())) {
                    Node c = new Node(entry.getKey(), null);
                    int count = 1;
                    c.countDes = 0;
                    all.add(c);
                    String value = entry.getValue();
                    Node current = null;
                    while (value != null || current != null) {
                        Node p;
                        if (current != null) {
                            p = current;
                            p.countDes += count;
                            current = p.p;
                            value = null;
                            continue;
                        }
                        String finalValue = value;
                        Optional<Node> pOpt = all.stream().filter(e -> e.name.equals(finalValue)).findFirst();
                        if (pOpt.isPresent()) {
                            p = pOpt.get();
                            p.countDes += count;
                            current = p.p;
                            value = null;
                            c.p = p;
                        } else {
                            p = new Node(value, null);
                            c.p = p;
                            p.countDes += count;
                            count++;
                            all.add(p);

                            c = p;
                            value = childParent.get(c.name);
                        }
                    }
                }
            }
            Collections.sort(all, Comparator.comparing(e -> e.name));
            for (Node e : all) {
                writer.println(e.name + " " + e.countDes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
