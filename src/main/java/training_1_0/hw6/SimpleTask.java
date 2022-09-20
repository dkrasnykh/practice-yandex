package training_1_0.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Pattern;

public class SimpleTask {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            Pattern regex = Pattern.compile(" ");
            String[] nxy = regex.split(reader.readLine());
            int n = Integer.parseInt(nxy[0]);
            int x = Integer.parseInt(nxy[1]);
            int y = Integer.parseInt(nxy[2]);
            int slower;
            int faster;
            if (x < y) {
                slower = y;
                faster = x;
            } else {
                slower = x;
                faster = y;
            }
            int firstCopyTime = faster;
            n--;
            int i = 0;
            int tempn = n;
            int fasterTime = n * faster;
            int lastAction = n*slower;
            while (i <= n) {
                tempn = n;
                int action1 = slower * i;
                tempn -= i;
                int action2 = tempn * faster;
                int action = (action1 < action2) ? action2 : action1;
                //System.out.println(action);
                if (lastAction < action) {
                    break;
                }
                i++;
                lastAction = action;
            }
            writer.println((fasterTime < lastAction) ? fasterTime + firstCopyTime : lastAction + firstCopyTime);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
