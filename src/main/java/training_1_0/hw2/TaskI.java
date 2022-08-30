package training_1_0.hw2;

import java.util.Scanner;

public class TaskI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] a = in.nextLine().split(" ");
        int n = Integer.parseInt(a[0]);
        int m = Integer.parseInt(a[1]);
        int k = Integer.parseInt(a[2]);
        int[][] f = new int[n][m];
        for (int i = 0; i < k; i++) {
            a = in.nextLine().split(" ");
            int p = Integer.parseInt(a[0]);
            int q = Integer.parseInt(a[1]);
            f[p - 1][q - 1] = -1;
        }
        if (k > 0) {
            buildPlayingField(f);
        }
        print(f);
    }
    static void print(int[][] f) {
        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length - 1; j++) {
                System.out.print((f[i][j] == -1) ? "* " : f[i][j] + " ");
            }
            System.out.println((f[i][f[i].length - 1] == -1) ? "*" : f[i][f[i].length - 1]);
        }
    }
    static void buildPlayingField(int[][] f) {
        if (f.length == 1 && f[0].length == 1) {
            return;
        }
        if (f.length == 1) {
            f[0][0] = (f[0][0] != -1) ? ((f[0][1] == -1) ? 1 : 0) : -1;
            f[0][f[0].length - 1] = (f[0][f[0].length - 1] != -1) ? ((f[0][f[0].length - 2] == -1) ? 1 : 0) : -1;
            for (int i = 1; i < f[0].length - 1; i++) {
                if (f[0][i] != -1) {
                    if (f[0][i - 1] == -1) {
                        f[0][i]++;
                    }
                    if (f[0][i + 1] == -1) {
                        f[0][i]++;
                    }
                }
            }
            return;
        }
        if (f[0].length == 1) {
            f[0][0] = (f[0][0] != -1) ? ((f[1][0] == -1) ? 1 : 0) : -1;
            f[f.length - 1][0] = (f[f.length - 1][0] != -1) ? (f[f.length - 2][0] == -1 ? 1 : 0) : -1;
            for (int i = 1; i < f.length - 1; i++) {
                if (f[i][0] != -1) {
                    if (f[i - 1][0] == -1) {
                        f[i][0]++;
                    }
                    if (f[i + 1][0] == -1) {
                        f[i][0]++;
                    }
                }
            }
            return;
        }

        for (int i = 0; i < f.length; i++) {
            for (int j = 0; j < f[i].length; j++) {
                if (i == 0) {
                    if (j == 0) {
                        if (f[i][j] != -1) {
                            f[i][j] += (f[i][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j] == -1) ? 1 : 0;
                        }
                    } else if (j == f[i].length - 1) {
                        if (f[i][j] != -1) {
                            f[i][j] += (f[i][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j] == -1) ? 1 : 0;
                        }
                    } else {
                        if (f[i][j] != -1) {
                            f[i][j] += (f[i][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i + 1][j] == -1) ? 1 : 0;
                        }
                    }
                } else if (i == f.length - 1) {
                    if (j == 0) {
                        if (f[i][j] != -1) {
                            f[i][j] += (f[i][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j] == -1) ? 1 : 0;

                        }
                    } else if (j == f[i].length - 1) {
                        if (f[i][j] != -1) {
                            f[i][j] += (f[i][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j] == -1) ? 1 : 0;
                        }
                    } else {
                        if (f[i][j] != -1) {
                            f[i][j] += (f[i][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j - 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j + 1] == -1) ? 1 : 0;
                            f[i][j] += (f[i - 1][j] == -1) ? 1 : 0;
                        }
                    }
                } else if (j == 0) {
                    if (f[i][j] != -1) {
                        f[i][j] += (f[i - 1][j] == -1) ? 1 : 0;
                        f[i][j] += (f[i - 1][j + 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i][j + 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j + 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j] == -1) ? 1 : 0;
                    }
                } else if (j == f[i].length - 1) {
                    if (f[i][j] != -1) {
                        f[i][j] += (f[i - 1][j] == -1) ? 1 : 0;
                        f[i][j] += (f[i - 1][j - 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i][j - 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j - 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j] == -1) ? 1 : 0;
                    }
                } else {
                    if (f[i][j] != -1) {
                        f[i][j] += (f[i - 1][j] == -1) ? 1 : 0;
                        f[i][j] += (f[i - 1][j - 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i - 1][j + 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i][j - 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i][j + 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j - 1] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j] == -1) ? 1 : 0;
                        f[i][j] += (f[i + 1][j + 1] == -1) ? 1 : 0;
                    }
                }
            }
        }
    }
}
