package intensive.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class E {
    private static boolean check1(int m, int c, int[] stall) {
        return stall[m] <= c;
    }

    private static int lbinsearch(int l, int r, int c, int[] stall) {
        while (l < r) {
            int m = (l + r) / 2;
            if (check1(m, c, stall)) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }

    private static boolean check2(int m, int c, int[] stall) {
        return stall[m] >= c;
    }

    private static int rbinsearch(int l, int r, int c, int[] stall) {
        while (l < r) {
            int m = (l + r + 1) / 2;
            if (check2(m, c, stall)) {
                l = m;
            } else {
                r = m - 1;
            }
        }
        return l;
    }

    public static void main(String[] args) {
        /*
        1. Поместить коров в крайние точки
        2. получить расстояние и разделить его на оставщихся коров -1.
        3. для каждой коровы искать слева и справа ближайшую точку. Если она найдена, то помещать туда корову (если стойло не занято).
        */
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            String[] nk = regex.split(reader.readLine());
            int n = Integer.parseInt(nk[0]);
            int k = Integer.parseInt(nk[1]);
            String[] stalls = regex.split(reader.readLine());
            int[] stall = new int[n];
            for (int i = 0; i < stall.length; i++) {
                stall[i] = Integer.parseInt(stalls[i]);
            }
            if (k <= 2) {
                //ставим коров в первое и посленее стойло и выводим расстояние между ними
                System.out.println(stall[n - 1] - stall[0]);
                return;
            }
            if (k == n) {
                //выводим максимальное расстояние между стойлами
            }
            int d = (stall[n - 1] - stall[0]) / (k - 1);
            //создаём массив координат для существующих коров
            int[] newcoor = new int[k];
            int[] cowInStall = new int[n];
            newcoor[0] = stall[0];
            newcoor[k - 1] = stall[n - 1];
            for (int i = 1; i < k - 1; i++) {
                newcoor[i] = newcoor[i - 1] + d;
            }

            /*
            подставляем ответ и смотрим хорошо или плохо
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
