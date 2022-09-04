package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Jog1 {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = reader.readLine().split(" ");
            byte t = Byte.parseByte(input[0]);
            byte d = Byte.parseByte(input[1]);
            byte n = Byte.parseByte(input[2]);

            short[] x = new short[n];
            short[] y = new short[n];
            for (byte i = 0; i < n; i++) {
                input = reader.readLine().split(" ");
                x[i] = Short.parseShort(input[0]);
                y[i] = Short.parseShort(input[1]);
            }
            Set<short[]> startPoints = new HashSet<>();
            startPoints.add(new short[]{(short) 0, (short) 0});

            //for (Coordinates point : navigatorOutput) {
            for (int i = 0; i < n; i++) {

                Set<short[]> tPoints = new HashSet<>();
                for (short[] startPoint : startPoints) {
                    getPossiblePoints(tPoints, startPoint[0], startPoint[1], t);
                }

                Set<short[]> dPoints = new HashSet<>();
                getPossiblePoints(dPoints, x[i], y[i], d);

                tPoints.retainAll(dPoints);
                startPoints = tPoints;
            }
            System.out.println(startPoints.size());
            startPoints.forEach(System.out::println);
        }
    }

    static void getPossiblePoints(Set<short[]> set, short x, short y, byte d) {
        set.add(new short[]{x, y});
        short i1 = (short) (x - d);
        short i2 = (short) (x + d);
        short j1 = (short) (y - d);
        short j2 = (short) (y + d);

        for (short i = i1; i <= i2; i++) {
            for (short j = j1; j <= j2; j++) {
                if (isPointInDistance(x, y, i, j, d)) {
                    set.add(new short[]{i, j});
                }
            }
        }
    }

    static boolean isPointInDistance(short x1, short y1, short x2, short y2, byte t) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1) <= t;
    }
}

