package training_2_0_B.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class C_MinCoverage {
    static boolean intersect0M(int M, int l, int r) {
        return l >= 0 && l < M || r <= M && r > 0 || l <= 0 && r >= M;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int M = Integer.parseInt(reader.readLine());
            List<int[]> events = new ArrayList<>(2 * M + 1);
            String str;
            String[] line;

            while (true) {
                str = reader.readLine().replaceAll("[\\s]{2,}", " ").trim();
                if (str.equals("0 0")) {
                    break;
                }
                line = str.split(" ");
                int l = Integer.parseInt(line[0]);
                int r = Integer.parseInt(line[1]);
                if (intersect0M(M, l, r)) {
                    events.add(new int[]{l, -2, r});
                    events.add(new int[]{r, 2, l});
                }

            }
            events.add(new int[]{0, -1, M});
            events.add(new int[]{M, 1, 0});
            Collections.sort(events, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

            int[] lead = null;
            int[] next = null;
            List<int[]> ans = new ArrayList<>();
            for (int[] e : events) {
                if (e[1] == -2) {
                    if (e[0] <= 0 && lead == null) {
                        lead = e;
                        if (lead[2] >= M) {
                            ans.add(new int[]{lead[0], lead[2]});
                            break;
                        }
                    } else if (e[0] <= 0 && lead != null) {
                        lead = (lead[2] < e[2] ? e : lead);
                        if (lead[2] >= M) {
                            ans.add(new int[]{lead[0], lead[2]});
                            break;
                        }
                    } else {
                        if (lead[2] < e[0]) {
                            writer.println("No solution");
                            return;
                        }
                        if (next == null && e[2] > lead[2]) {
                            next = e;
                        } else if (next != null && e[2] > next[2]) {
                            next = e;
                        }
                    }
                } else if (e[1] == -1) {
                    if (lead == null) {
                        writer.println("No solution");
                        return;
                    }

                } else if (e[1] == 2) {
                    if (e[0] == lead[2] && e[2] == lead[0] && next == null) {
                        writer.println("No solution");
                        return;
                    } else if (e[0] == lead[2] && e[2] == lead[0] && next != null) {
                        ans.add(new int[]{lead[0], lead[2]});
                        lead = next;
                        if (lead[2] >= M) {
                            ans.add(new int[]{lead[0], lead[2]});
                            break;
                        }
                        next = null;
                    }
                } else if (e[1] == 1) {
                    if (next != null && next[2] >= M) {
                        ans.add(new int[]{next[0], next[2]});
                    } else if (lead != null && lead[2] >= M) {
                        ans.add(new int[]{lead[0], lead[2]});
                    } else {
                        writer.println("No solution");
                        return;
                    }
                    break;
                }
            }
            writer.println(ans.size());
            for (int[] e : ans) {
                writer.println(e[0] + " " + e[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
