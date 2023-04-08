package training_2_A.hw5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class B_Candies {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int k = Integer.parseInt(reader.readLine().trim());
            int[] a = new int[k];
            int[] n = new int[k];
            for (int i = 0; i < k; ++i) {
                String[] line = reader.readLine().trim().split(" ");
                a[i] = Integer.parseInt(line[0]);
                n[i] = Integer.parseInt(line[1]);
            }
            int l = 0, r = k - 1;
            long leftTail = 0, rightTail = 0, leftAdd = 0, rightAdd = 0;
            while (l <= r) {
                if (leftTail == 0) {
                    leftTail = leftAdd + a[l];
                    leftAdd = 0;
                    n[l] -= 1;
                }
                if (rightTail == 0 && n[r] > 0) {
                    rightTail = rightAdd + a[r];
                    rightAdd = 0;
                    n[r] -= 1;
                }

                if (l == r && n[l] == 0 || l + 1 == r && n[l] == 0 && n[r] == 0) {
                    if (rightAdd != 0) {
                        leftTail += rightAdd;
                    }
                    if (leftAdd != 0) {
                        rightTail += leftAdd;
                    }
                    if (leftTail != 0 && rightTail != 0) {
                        writer.println(2);
                        writer.printf("%d %d\n", leftTail, rightTail);
                    } else {
                        writer.println(1);
                        writer.println(leftTail != 0 ? leftTail : rightTail);
                    }
                }

                long min = Math.min(leftTail, rightTail);
                leftAdd += min;
                rightAdd += min;
                leftTail -= min;
                rightTail -= min;

                if (n[l] == 0) {
                    l += 1;
                }
                if (n[r] == 0) {
                    r -= 1;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
