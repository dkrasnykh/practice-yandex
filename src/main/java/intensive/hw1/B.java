package intensive.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class B {
    static String getCanonicalPath(List<String> dirs) {
        StringBuilder sb = new StringBuilder();
        if (dirs.size() == 0) {
            return sb.append("/").toString();
        }
        for (String d : dirs) {
            sb.append("/").append(d);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Pattern regex = Pattern.compile("/");
        try {
            String[] dirs = regex.split(reader.readLine());
            List<String> ans = new ArrayList<>();
            for (int i = 0; i < dirs.length; i++) {
                String dir = dirs[i].trim();
                if (dir.isEmpty() || dir.equals(".") || (dir.equals("..") && ans.size() == 0)) {
                    continue;
                }
                if (dir.equals("..")) {
                    ans.remove(ans.size() - 1);
                } else {
                    ans.add(dir);
                }
            }
            System.out.println(getCanonicalPath(ans));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
