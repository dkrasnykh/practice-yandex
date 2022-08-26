package training_1_0.hw2;

import java.util.Scanner;

public class TaskE {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine();
        if (n < 3) {
            System.out.println(0);
            return;
        }
        short[] a = getShortArrayFromString(s, n);
        short x = getTheBestVasyaThrowLengh(a);
        if (x == 0) {
            System.out.println(0);
            return;
        }
        heapsort(a);
        System.out.println(getPosition(a, x));
    }

    static short[] getShortArrayFromString(String s, int n) {
        short[] ans = new short[n];
        if (n == 1) {
            ans[0] = Short.parseShort(s);
            return ans;
        }
        String[] a = s.split(" ");
        for (int i = 0; i < n; i++) {
            ans[i] = Short.parseShort(a[i]);
        }
        return ans;
    }

    static short getTheBestVasyaThrowLengh(short[] a) {
        int itop = getWinnersThrowLength(a);
        short ans = 0;
        for (int i = 1; i < a.length - 1; i++) {
            if (a[i] % 10 == 5 && itop < i && a[i + 1] < a[i] && a[i] > ans) {
                ans = a[i];
            }
        }
        return ans;
    }

    static int getPosition(short[] a, short x) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == x) {
                return i + 1;
            }
        }
        return 0;
    }

    static int getWinnersThrowLength(short[] a) {
        int imax = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[imax]) {
                imax = i;
            }
        }
        return imax;
    }

    static int left(int i) {
        return 2 * i;
    }

    static int right(int i) {
        return 2 * i + 1;
    }

    static void maxHeapify(short[] a, int i, int heapsize) {
        int l = left(i + 1) - 1;
        int r = right(i + 1) - 1;
        int smallest;
        if (l < heapsize && a[l] < a[i]) {
            smallest = l;
        } else {
            smallest = i;
        }
        if (r < heapsize && a[r] < a[smallest]) {
            smallest = r;
        }
        if (i != smallest) {
            short temp = a[i];
            a[i] = a[smallest];
            a[smallest] = temp;
            maxHeapify(a, smallest, heapsize);
        }
    }

    static void buildMaxheapify(short[] a) {
        for (int i = a.length / 2; i >= 0; i--) {
            maxHeapify(a, i, a.length);
        }
    }

    static void heapsort(short[] a) {
        buildMaxheapify(a);
        int heapsize = a.length;
        for (int i = a.length - 1; i > 0; i--) {
            short temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapsize--;
            maxHeapify(a, 0, heapsize);
        }
    }
}
