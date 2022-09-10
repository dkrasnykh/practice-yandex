package intensive.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class A {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile(" ");
        try {
            int n = Integer.parseInt(reader.readLine());
            String[] groups = regex.split(reader.readLine());
            int m = Integer.parseInt(reader.readLine());
            String[] rooms = regex.split(reader.readLine());
            int[] group = new int[groups.length];
            int[] room = new int[rooms.length];
            for (int i = 0; i < groups.length; i++) {
                group[i] = Integer.parseInt(groups[i]);
            }
            for (int i = 0; i < rooms.length; i++) {
                room[i] = Integer.parseInt(rooms[i]);
            }
            Arrays.sort(group);
            Arrays.sort(room);
            int ig = 0;
            int ir = 0;
            int count = 0;
            while (ir < room.length && ig < group.length) {
                if (group[ig] <= room[ir]) {
                    count++;
                    ig++;
                    ir++;
                } else {
                    ir++;
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
