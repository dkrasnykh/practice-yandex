package intensive.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D1 {
    static int dfs(int now, List<Integer>[] neighbors, int[] subtreesize) {
        subtreesize[now] = 1;
        for (Integer next : neighbors[now]) {
            if (subtreesize[next] == -1) {
                subtreesize[now] += dfs(next, neighbors, subtreesize);
            }
        }
        return subtreesize[now];
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            List<Integer>[] neighbors = new List[n + 1];
            for (int i = 0; i < n + 1; i++) {
                neighbors[i] = new ArrayList<>();
            }
            String[] line;
            int a, b;
            for (int i = 0; i < n - 1; i++) {
                line = reader.readLine().trim().split(" ");
                a = Integer.parseInt(line[0]);
                b = Integer.parseInt(line[1]);
                neighbors[a].add(b);
                neighbors[b].add(a);
            }
            int[] subtreesize = new int[n + 1];
            Arrays.fill(subtreesize, -1);
            dfs(1, neighbors, subtreesize);
            for (int i = 1; i < subtreesize.length; i++) {
                writer.print(subtreesize[i] + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
