package training_3_0.divA.bfs.robots_39;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//1 - если зал, 0 - если туннель
class Vertex{
    private int ind, type;

    public Vertex(int ind, int type) {
        this.ind = ind;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vertex vertex = (Vertex) o;
        return ind == vertex.ind && type == vertex.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ind, type);
    }

    public int getInd() {
        return ind;
    }

    public int getType() {
        return type;
    }
}

public class Robots39 {
    private static List<Vertex>[] adjList;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            int n = Integer.parseInt(line[0]);
            int k = Integer.parseInt(line[1]);
            for (int i = 1; i <= k; ++i) {
                line = reader.readLine().split(" ");
                int a = Integer.parseInt(line[0]);
                int b = Integer.parseInt(line[1]);
                //adjList[a].add(b);
                //adjList[b].add(a);
            }




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
