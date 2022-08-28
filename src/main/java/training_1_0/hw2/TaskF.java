package training_1_0.hw2;

import java.util.Scanner;

public class TaskF {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String s = in.nextLine().trim();

        if (n == 1) {
            System.out.println(0);
            return;
        }

        byte[] a = getByteArrayFromString(s);

        int pos = -1;
        int p = (a.length % 2 == 0) ? a.length / 2 : a.length / 2 + 1;
        for (int i = p; i < a.length; i++) {
            int res = checkSeq(a, i);
            if (res == 3) {
                int posEven = 2 * i - a.length;
                int posOdd = 2 * i - a.length - 1;
                pos = (posEven == 0) ? posEven : posOdd;
                break;
            }
            if (res == 1) {
                pos = 2 * i - a.length - 1;
                break;
            }
            if (res == 0) {
                pos = 2 * i - a.length;
                break;
            }
        }

        if (pos >= 0) {
            System.out.println(pos);
            for (int j = pos - 1; j > 0; j--) {
                System.out.print(a[j] + " ");
            }
            if (pos > 0) {
                System.out.println(a[0]);
            }
            return;
        }

        System.out.println(a.length - 1);
        for (int i = a.length - 2; i > 0; i--) {
            System.out.print(a[i] + " ");
        }
        System.out.println(a[0]);
    }

    static int checkSeq(byte[] a, int p) {
        if (a.length == 2) {
            return (a[0] == a[1]) ? 0 : -1;
        }
        boolean symEven = true;
        boolean symOdd = true;
        int d = 0;
        for (int i = p; i < a.length; i++) {
            if (p - 2 - d < 0 || a[i] != a[p - 2 - d]) {
                symOdd = false;
            }
            if (a[i] != a[p - d - 1]) {
                symEven = false;
            }
            d++;
        }

        if (symEven && symOdd) {
            return 3;
        } else if (symOdd) {
            return 1;
        } else if (symEven) {
            return 0;
        }
        return -1;
    }

    static byte[] getByteArrayFromString(String s) {
        String[] a = s.split(" ");
        byte[] ans = new byte[a.length];
        for (int i = 0; i < a.length; i++) {
            ans[i] = Byte.parseByte(a[i]);
        }
        return ans;
    }
}