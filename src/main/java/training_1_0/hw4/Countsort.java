package training_1_0.hw4;

import java.util.Arrays;

public class Countsort {
    static void countsort(int[] seq) {
        int minVal = Arrays.stream(seq).min().getAsInt();
        int maxVal = Arrays.stream(seq).max().getAsInt();
        int k = maxVal - minVal + 1;
        int[] count = new int[k];
        for (int i = 0; i < seq.length; i++) {
            count[seq[i] - minVal] += 1;
        }
        int npos = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < count[i]; j++) {
                seq[npos] = i + minVal;
                npos++;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {7, 1, -2, 0, 0, 8};
        countsort(a);
        System.out.println(Arrays.toString(a));

    }
}
