package intensive.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class D {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] numStr = regex.split(reader.readLine());
            Map<Integer, Integer> mnum = new HashMap<>();
            for (int i = 0; i < numStr.length; i++) {
                int num = Integer.parseInt(numStr[i]);
                int count = mnum.getOrDefault(num, 0);
                if (count + 1 > n / 2) {
                    System.out.println(num);
                    return;
                }
                mnum.put(num, count + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
