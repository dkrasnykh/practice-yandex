package training_2_A.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeSet;

class Pair {
    private int first;
    private int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return first == pair.first && second == pair.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }
}

public class D {
    private static int INF = 500;

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine().trim());

            TreeSet<Pair> tree = new TreeSet<>((a, b) -> (a.getFirst() == b.getFirst()) ? (a.getSecond() - b.getSecond()) : (b.getFirst() - a.getFirst()));
            String[] line = reader.readLine().trim().split(" ");
            int[] lastPoints = new int[n];
            for (int i = 0; i < n - 1; ++i) {
                lastPoints[i] = Integer.parseInt(line[i]);
                tree.add(new Pair(Integer.parseInt(line[i]), i));
            }
            lastPoints[n - 1] = Integer.parseInt(line[n - 1]);
            int points = Integer.parseInt(line[n - 1]);
            Pair targetPoints = new Pair(points, n - 1);

            tree.add(targetPoints);

            HashMap<Integer, Integer> map = new HashMap<>();
            HashMap<Integer, Pair> ind = new HashMap<>();
            line = reader.readLine().trim().split(" ");
            for (int i = 0; i < n - 1; ++i) {
                int key1 = Integer.parseInt(line[i]);
                map.compute(key1, (key, value) -> (value == null) ? 1 : value + 1);
                ind.put(key1, new Pair(i, lastPoints[i]));
            }
            int minUniqueValue = INF;
            TreeSet<Integer> unique = new TreeSet<>();
            for (Map.Entry<Integer, Integer> e : map.entrySet()) {
                if (e.getValue() == 1) {
                    minUniqueValue = Math.min(e.getKey(), minUniqueValue);
                    unique.add(e.getKey());
                }
            }
            int lower1, lower2 = -1, lower3 = -1, start, ans, under;
            Integer next_min_unique = null;
            lower1 = tree.tailSet(targetPoints, false).size();
            if (minUniqueValue != INF) {
                tree.remove(new Pair(ind.get(minUniqueValue).getSecond(), ind.get(minUniqueValue).getFirst()));
                tree.add(new Pair(ind.get(minUniqueValue).getSecond() + minUniqueValue, ind.get(minUniqueValue).getFirst()));
                lower2 = tree.tailSet(targetPoints, false).size();
                tree.remove(new Pair(ind.get(minUniqueValue).getSecond() + minUniqueValue, ind.get(minUniqueValue).getFirst()));
                tree.add(new Pair(ind.get(minUniqueValue).getSecond(), ind.get(minUniqueValue).getFirst()));

                start = minUniqueValue;
                ans = minUniqueValue + 1;
                under = lower2;

                next_min_unique = unique.higher(minUniqueValue);
                if (next_min_unique != null) {
                    tree.remove(new Pair(ind.get(next_min_unique).getSecond(), ind.get(next_min_unique).getFirst()));
                    tree.add(new Pair(ind.get(next_min_unique).getSecond() + next_min_unique, ind.get(next_min_unique).getFirst()));
                    lower3 = tree.tailSet(targetPoints, false).size();
                    tree.remove(new Pair(ind.get(next_min_unique).getSecond() + next_min_unique, ind.get(next_min_unique).getFirst()));
                    tree.add(new Pair(ind.get(next_min_unique).getSecond(), ind.get(next_min_unique).getFirst()));
                }
            } else {
                start = tree.first().getFirst() - targetPoints.getFirst() + 1;
                ans = start;
                under = 0;
            }

            for (int i = start; i >= 1; --i) {
                if (map.containsKey(i)) {
                    if (i == minUniqueValue) {
                        if (lower3 != -1 && under <= lower3) {
                            under = lower3;
                            ans = i;
                        } else if (lower3 == -1 && under <= lower1) {
                            under = lower1;
                            ans = i;
                        }
                    } else {
                        if (lower2 != -1 && under <= lower2) {
                            under = lower2;
                            ans = i;
                        } else if (lower2 == -1 && under <= lower1) {
                            under = lower1;
                            ans = i;
                        }
                    }
                } else {
                    tree.remove(targetPoints);
                    Pair newTarget = new Pair(points + i, n - 1);
                    tree.add(newTarget);
                    int tmpLower = tree.tailSet(newTarget, false).size();
                    if (under <= tmpLower) {
                        under = tmpLower;
                        ans = i;
                    }
                    tree.remove(newTarget);
                    tree.add(targetPoints);
                }
            }
            writer.println(ans);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
