package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TaskJ {
    static short[] extend(short[] rect, int d) {
        short minPlus = rect[0];
        short maxPlus = rect[1];
        short minMinus = rect[2];
        short maxMinus = rect[3];
        rect[0] = (short) (minPlus - d);
        rect[1] = (short) (maxPlus + d);
        rect[2] = (short) (minMinus - d);
        rect[3] = (short) (maxMinus + d);
        return rect;
    }

    static short[] intersect(short[] rect1, short[] rect2) {
        short[] ans = new short[4];
        ans[0] = (short) Math.max(rect1[0], rect2[0]);
        ans[1] = (short) Math.min(rect1[1], rect2[1]);
        ans[2] = (short) Math.max(rect1[2], rect2[2]);
        ans[3] = (short) Math.min(rect1[3], rect2[3]);
        return ans;
    }

    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = regex.split(reader.readLine());
            byte t = Byte.parseByte(input[0]);
            byte d = Byte.parseByte(input[1]);
            byte n = Byte.parseByte(input[2]);

            short[] posRect = {0, 0, 0, 0};

            for (byte i = 0; i < n; i++) {
                input = regex.split(reader.readLine());
                extend(posRect, t);
                short navX = Short.parseShort(input[0]);
                short navY = Short.parseShort(input[1]);
                short[] navRect = {(short) (navX + navY), (short) (navX + navY), (short) (navX - navY), (short) (navX - navY)};
                extend(navRect, d);
                posRect = intersect(posRect, navRect);
            }
            reader.close();

            List<short[]> points = new ArrayList<>();

            for (int xPlusY = posRect[0]; xPlusY <= posRect[1]; xPlusY++) {
                for (int xMinusY = posRect[2]; xMinusY <= posRect[3]; xMinusY++) {
                    if ((xPlusY + xMinusY) % 2 == 0) {
                        short x = (short) ((xPlusY + xMinusY) / 2);
                        short y = (short) (xPlusY - x);
                        points.add(new short[]{x, y});
                    }
                }
            }

            PrintWriter writer = new PrintWriter(System.out);
            writer.println(points.size());
            points.forEach(p -> writer.println(p[0]+" "+p[1]));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
