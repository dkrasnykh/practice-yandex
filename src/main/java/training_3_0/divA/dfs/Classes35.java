package training_3_0.divA.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

class HeapMin {
    private List<int[]> a;
    private Map<Integer, Integer> mapInd;

    private void swap(int v, int u) {
        int[] tmp = a.get(v);
        a.set(v, a.get(u));
        a.set(u, tmp);
        mapInd.put(a.get(u)[0], u);
        mapInd.put(a.get(v)[0], v);
    }

    private void siftUp(int v) {
        while (v != 1) {
            if (a.get(v)[1] < a.get(v / 2)[1]) {
                swap(v, v / 2);
                v /= 2;
            } else {
                break;
            }
        }
    }

    private void siftDown(int v) {
        int n = a.size() - 1;
        while (v * 2 <= n) {
            int u = v * 2;
            if (v * 2 + 1 <= n && (a.get(v * 2 + 1)[1] < a.get(u)[1])) {
                u = v * 2 + 1;
            }
            if (a.get(u)[1] < a.get(v)[1]) {
                swap(u, v);
                v = u;
            } else {
                break;
            }
        }
    }

    public HeapMin() {
        this.a = new ArrayList<>();
        this.a.add(new int[]{0, 0});
        this.mapInd = new HashMap<>();
    }

    public boolean isEmpty() {
        return a.size() == 1;
    }

    public int getId(int v) {
        return mapInd.get(v);
    }

    public boolean containsId(int v) {
        return mapInd.containsKey(v);
    }

    public int getMin() {
        return this.a.get(1)[1];
    }

    public void decreaseKey(int i, int delta) {
        int[] e = this.a.get(i);
        e[1] -= delta;
        this.a.set(i, e);
        siftUp(i);
    }


    public void insert(int[] x) {
        a.add(x);
        mapInd.put(x[0], a.size() - 1);
        siftUp(a.size() - 1);
    }

    public int[] extractMin() {
        int[] ans = a.get(1);
        int n = a.size() - 1;
        swap(1, n);
        mapInd.remove(a.get(n)[0]);
        a.remove(n);
        siftDown(1);
        return ans;
    }

}

public class Classes35 {
    private static List<Integer>[] adjList;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {

            int n = Integer.parseInt(reader.readLine());
            adjList = new List[n + 1];
            for (int i = 1; i <= n; ++i) {
                adjList[i] = new ArrayList<>();
            }
            HeapMin heap = new HeapMin();
            for (int i = 1; i <= n; ++i) {
                String[] line = reader.readLine().split(" ");
                int k = Integer.parseInt(line[0]);
                heap.insert(new int[]{i, k});
                for (int j = 1; j <= k; ++j) {
                    int v = Integer.parseInt(line[j]);
                    adjList[v].add(i);
                }
            }
            PriorityQueue<Integer> q = new PriorityQueue<>();
            List<Integer> ans = new ArrayList<>();
            while (!heap.isEmpty()) {
                while (!heap.isEmpty() && heap.getMin() == 0) {
                    int[] e = heap.extractMin();
                    q.add(e[0]);
                }
                int v = q.poll();
                ans.add(v);
                for (Integer e : adjList[v]) {
                    if (heap.containsId(e)) {
                        heap.decreaseKey(heap.getId(e), 1);
                    }
                }
                while (!q.isEmpty()) {
                    heap.insert(new int[]{q.poll(), 0});
                }
            }
            for (Integer e : ans) {
                writer.print(e + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
