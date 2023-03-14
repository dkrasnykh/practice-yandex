package training_3_0.divA.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Heap {
    public static int left(int i) {
        return 2 * i;
    }
    public static int right(int i) {
        return 2 * i + 1;
    }
    public static void maxHeapify(int[][] a, int i, int heapSize) {
        int l = left(i + 1) - 1;
        int r = right(i + 1) - 1;
        int largest;
        if (l < heapSize && a[0][l] > a[0][i]) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < heapSize && a[0][r] > a[0][largest]) {
            largest = r;
        }
        if (largest != i) {
            int temp = a[0][i];
            a[0][i] = a[0][largest];
            a[0][largest] = temp;
            temp = a[1][i];
            a[1][i] = a[1][largest];
            a[1][largest] = temp;
            temp = a[2][i];
            a[2][i] = a[2][largest];
            a[2][largest] = temp;
            maxHeapify(a, largest, heapSize);
        }
    }
    public static void buildMaxHeap(int[][] a) {
        for (int i = a[0].length / 2 - 1; i >= 0; i--) {
            maxHeapify(a, i, a[0].length);
        }
    }
    public static void heapsort(int[][] a) {
        buildMaxHeap(a);
        int heapSize = a[0].length;
        for (int i = a[0].length - 1; i >= 1; i--) {
            int temp = a[0][0];
            a[0][0] = a[0][i];
            a[0][i] = temp;

            temp = a[1][0];
            a[1][0] = a[1][i];
            a[1][i] = temp;

            temp = a[2][0];
            a[2][0] = a[2][i];
            a[2][i] = temp;

            heapSize--;
            maxHeapify(a, 0, heapSize);
        }
    }
}

public class Landing34 {
    private static int[][] map;
    private static int n, m;
    private static boolean[][] used;
    private static List<int[]> next(int i, int j) {
        List<int[]> result = new ArrayList<>();
        if (map[i - 1][j] >= map[i][j]) {
            result.add(new int[]{i - 1, j});
        }
        if (map[i][j + 1] >= map[i][j]) {
            result.add(new int[]{i, j + 1});
        }
        if (map[i + 1][j] >= map[i][j]) {
            result.add(new int[]{i + 1, j});
        }
        if (map[i][j - 1] >= map[i][j]) {
            result.add(new int[]{i, j - 1});
        }
        return result;
    }
    private static void dfs(int i, int j) {
        used[i][j] = true;
        for (int[] u : next(i, j)) {
            if (!used[u[0]][u[1]]) {
                dfs(u[0], u[1]);
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            n = Integer.parseInt(line[0]);
            m = Integer.parseInt(line[1]);
            map = new int[n + 2][m + 2];
            used = new boolean[n + 2][m + 2];
            int[][] height = new int[3][n * m];
            Arrays.fill(map[0], -1);
            Arrays.fill(map[n + 1], -1);
            int size = 0;
            for (int i = 1; i <= n; ++i) {
                line = reader.readLine().split(" ");
                map[i][0] = -1;
                map[i][m + 1] = -1;
                for (int j = 1; j <= m; ++j) {
                    int value = Integer.parseInt(line[j - 1]);
                    map[i][j] = value;
                    height[0][size] = value;
                    height[1][size] = i;
                    height[2][size] = j;
                    ++size;
                }
            }
            Heap.heapsort(height);
            int cnt = 0;
            for (int i = 0; i < height[0].length; ++i) {
                if (!used[height[1][i]][height[2][i]]) {
                    dfs(height[1][i], height[2][i]);
                    ++cnt;
                }
            }
            writer.println(cnt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
