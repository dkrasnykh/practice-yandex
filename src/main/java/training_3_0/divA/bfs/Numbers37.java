package training_3_0.divA.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

public class Numbers37 {
    private static Queue<Integer> q;
    private static Map<Integer, Integer> p;
    private static int leftRotate(int n) {
        int n1000 = n / 1000;
        int n100 = (n % 1000) / 100;
        int n10 = (n % 100) / 10;
        int n1 = n % 10;
        return n100 * 1000 + n10 * 100 + n1 * 10 + n1000;
    }

    private static int rightRotate(int n) {
        int n1000 = n / 1000;
        int n100 = (n % 1000) / 100;
        int n10 = (n % 100) / 10;
        int n1 = n % 10;
        return n1 * 1000 + n1000 * 100 + n100 * 10 + n10;
    }

    private static int increase(int n) {
        return (n / 1000 < 9) ? n + 1000 : n;
    }

    private static int decrease(int n) {
        return (n % 10 > 1) ? n - 1 : n;
    }

    private static List<Integer> restore(int target){
        List<Integer> ans = new ArrayList<>();
        int parent = p.get(target);
        ans.add(target);
        while (parent != -1) {
            ans.add(parent);
            parent = p.get(parent);
        }
        Collections.reverse(ans);
        return ans;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int target = Integer.parseInt(reader.readLine());
            q = new ArrayDeque<>();
            p = new HashMap<>();
            q.offer(n);
            p.put(n, -1);
            while (!q.isEmpty()) {
                int x = q.poll();
                if (x == target) {
                    break;
                }
                List<Integer> next = Arrays.asList(increase(x), decrease(x), rightRotate(x), leftRotate(x));
                for (Integer y : next) {
                    if (!p.containsKey(y)) {
                        q.offer(y);
                        p.put(y, x);
                    }
                }
            }
            List<Integer> ans = restore(target);
            for(Integer v : ans){
                writer.println(v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
