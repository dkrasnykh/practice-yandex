package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class D3 {
    static int left(int i) {
        return 2 * i;
    }

    static int right(int i) {
        return 2 * i + 1;
    }

    static void maxHeapify(int[] a, int i, int heapsize) {
        int l = left(i + 1) - 1;
        int r = right(i + 1) - 1;
        int smallest;
        if (l < heapsize && a[l] > a[i]) {
            smallest = l;
        } else {
            smallest = i;
        }
        if (r < heapsize && a[r] > a[smallest]) {
            smallest = r;
        }
        if (i != smallest) {
            int temp = a[i];
            a[i] = a[smallest];
            a[smallest] = temp;
            maxHeapify(a, smallest, heapsize);
        }
    }

    static void buildMaxheapify(int[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            maxHeapify(a, i, a.length);
        }
    }

    static void heapsort(int[] a) {
        buildMaxheapify(a);
        int heapsize = a.length;
        for (int i = a.length - 1; i > 0; i--) {
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapsize--;
            maxHeapify(a, 0, heapsize);
        }
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            if (n == 1) {
                System.out.println(1440);
            }
            String[] input = regex.split(reader.readLine());
            int[] minutes = new int[input.length];
            for (int i = 0; i < input.length; i++) {
                String[] hm = input[i].split(":");
                minutes[i] = Integer.parseInt(hm[0]) * 60 + Integer.parseInt(hm[1]);
            }
            heapsort(minutes);
            int min = minutes[1] - minutes[0];
            for (int i = 2; i < minutes.length; i++) {
                if (minutes[i] - minutes[i - 1] < min) {
                    min = minutes[i] - minutes[i - 1];
                }
            }
            int boundary = 24 * 60 - minutes[minutes.length - 1] + minutes[0];
            System.out.println((min < boundary) ? min : boundary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
