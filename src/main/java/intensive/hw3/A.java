package intensive.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class A {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String s1 = reader.readLine();
            String s2 = reader.readLine();
            if(s1.isEmpty() && s2.isEmpty()){
                System.out.println("NO");
                return;
            }
            char[] s1c = new char[s1.length()];
            char[] s2c = new char[s2.length()];
            s1.getChars(0, s1.length(), s1c, 0);
            s2.getChars(0, s2.length(), s2c, 0);
            Arrays.sort(s1c);
            Arrays.sort(s2c);
            StringBuilder sb1 = new StringBuilder();
            sb1.append(s1c);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(s2c);
            if (sb1.toString().equals(sb2.toString())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
