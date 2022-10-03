package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Midian2v1 {
    public static int lbinsearch(int l, int r, int[] arr, int key) {
        while (l < r) {
            int m = (l + r) / 2;
            if (arr[m] >= key) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    public static boolean isMedian(int m, int[] seq1, int[] seq2, int lenght) {
        int i1 = lenght - 1;
        int i2 = lenght - 1;
        if (m >= seq1[0] && m <= seq1[lenght - 1]) {
            i1 = lbinsearch(0, lenght - 1, seq1, m);
        }
        if (i1 == lenght - 1 && m != seq1[lenght - 1] || i1 == 0 && m != seq1[0]) { //заменить условия на m>=  m<=
            i2 = lbinsearch(0, lenght - 1, seq2, m);
        } else {
            if (seq2[lenght - i1 - 1] >= m) {
                return true;
            } else {
                return false;
            }
        }
        if (i2 == lenght - 1 && m != seq2[lenght - 1] || i2 == 0 && m != seq2[0]) {
            if (seq1[lenght - 1] < seq2[0] && m > seq1[lenght - 1] && m < seq2[0]) {
                return false;
            }
            if (seq2[lenght - 1] < seq1[0] && m > seq2[lenght - 1] && m < seq1[0]) {
                return false;
            }
            if (i1 == lenght - 1 && seq2[lenght - i1 - 1] >= m) {
                return true;
            }
            if (i2 == lenght - 1 && seq1[lenght - i2 - 1] >= m) {
                return true;
            }
        } else {
            if (seq1[lenght - i2 - 1] >= m) {
                return true;
            }
        }
        return false;
    }

    public static int binsearch(int l, int r, int[] seq1, int[] seq2, int lenght) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (isMedian(m, seq1, seq2, lenght)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    static int[] buildArray(int x1, int d1, int a, int c, int m, int l) {
        int[] d = new int[l];
        int[] x = new int[l];
        x[0] = x1;
        d[0] = d1;
        for (int i = 1; i < l; i++) {
            d[i] = (a * d[i - 1] + c) % m;
            x[i] = x[i - 1] + d[i - 1];
        }
        return x;
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String nl = reader.readLine();
            int space = nl.indexOf(" ");
            int n = Integer.parseInt(nl.substring(0, space));
            int lv = Integer.parseInt(nl.substring(space + 1));
            int[][] l = new int[n][];
            String[] line;
            String str;

            if (lv == 1) {
                int[] l1 = new int[n];
                for (int i = 0; i < n; i++) {
                    str = reader.readLine().trim().replaceAll(" +", " ");
                    if (str.isEmpty()) {
                        break;
                    }
                    //line = regex.split(str);
                    //line = str.split("[ ]{2,}");
                    line = str.split(" ");
                    l1[i] = Integer.parseInt(line[0]);
                }
                for (int i = 0; i < n - 1; i++) {
                    for (int j = i + 1; j < n; j++) {
                        writer.println(Math.min(l1[i], l1[j]));
                    }
                }
                return;
            }

            for (int i = 0; i < n; i++) {
                str = reader.readLine().trim().replaceAll(" +", " ");
                if (str.isEmpty()) {
                    break;
                }
                //String[] words = text.split("[ ]{2,}");
                //line = str.split("[ ]{2,}");
                //line = regex.split(" ");
                line = str.split(" ");
                l[i] = buildArray(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]),
                            Integer.parseInt(line[3]), Integer.parseInt(line[4]), lv);
            }

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    int lb = Math.min(l[i][0], l[j][0]);
                    int rb = Math.max(l[i][lv - 1], l[j][lv - 1]);
                    int ans = binsearch(lb, rb, l[i], l[j], lv);
                    writer.println(ans);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
