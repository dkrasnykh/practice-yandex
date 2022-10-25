package training_2_0_B.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class B_HousesAndShops {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().trim().split(" ");
            int[] a = new int[line.length];
            for (int i = 0; i < line.length; i++) {
                a[i] = Integer.parseInt(line[i]);
            }
            int iStoreBefore = -1;
            int iStoreAfter = -1;
            int max = 0;
            for (int i = 0; i < a.length; i++) {
                if (a[i] == 2) {
                    iStoreBefore = i;
                }
                if (a[i] == 1) {
                    if (iStoreAfter < i) {
                        for (int j = i + 1; j < a.length; j++) {
                            if (a[j] == 2) {
                                iStoreAfter = j;
                                break;
                            }
                        }
                    }
                    if (iStoreBefore != -1 && iStoreAfter != -1) {
                        max = Math.max(max, Math.min(i - iStoreBefore, Math.abs(iStoreAfter - i)));
                    } else if (iStoreBefore != -1){
                        max = Math.max(max, i-iStoreBefore);
                    } else if (iStoreAfter!=-1){
                        max = Math.max(max,  Math.abs(iStoreAfter - i));
                    }
                }
            }
            writer.println(max);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
