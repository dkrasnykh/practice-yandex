package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

class Coordinates {
    private short x;
    private short y;

    public Coordinates(short x, short y) {
        this.x = x;
        this.y = y;
    }

    public short getX() {
        return x;
    }

    public short getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Coordinates coordinates = (Coordinates) o;
        return x == coordinates.x && y == coordinates.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

public class Jog {
    public static void main(String[] args) {
        Pattern regex = Pattern.compile(" ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] input = regex.split(reader.readLine());
            byte t = Byte.parseByte(input[0]);
            byte d = Byte.parseByte(input[1]);
            byte n = Byte.parseByte(input[2]);

            short[] x = new short[n];
            short[] y = new short[n];
            for (byte i = 0; i < n; i++) {
                input = regex.split(reader.readLine());
                x[i] = Short.parseShort(input[0]);
                y[i] = Short.parseShort(input[1]);
            }
            reader.close();

            Set<Coordinates> startPoints = new HashSet<>();
            startPoints.add(new Coordinates((short) 0, (short) 0));

            for (byte i = 0; i < n; i++) {
                Set<Coordinates> tPoints = new HashSet<>();
                for (Coordinates startPoint : startPoints) {
                    getPossiblePoints(tPoints, startPoint.getX(), startPoint.getY(), t);
                }
                Set<Coordinates> dPoints = new HashSet<>();
                getPossiblePoints(dPoints, x[i], y[i], d);
                tPoints.retainAll(dPoints);
                
                startPoints = tPoints;
            }
            PrintWriter writer = new PrintWriter(System.out);
            writer.println(startPoints.size());
            startPoints.forEach(writer::println);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void getPossiblePoints(Set<Coordinates> set, short x, short y, byte d) {
        set.add(new Coordinates(x, y));
        short i1 = (short) (x - d);
        short i2 = (short) (x + d);
        short j1 = (short) (y - d);
        short j2 = (short) (y + d);

        for (short i = i1; i <= i2; i++) {
            for (short j = j1; j <= j2; j++) {
                if (isPointInDistance(x, y, i, j, d)) {
                    set.add(new Coordinates(i, j));
                }
            }
        }
    }

    static boolean isPointInDistance(short x1, short y1, short x2, short y2, byte t) {
        return Math.abs(x2 - x1) + Math.abs(y2 - y1) <= t;
    }
}
