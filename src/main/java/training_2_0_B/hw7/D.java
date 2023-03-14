package training_2_0_B.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class D {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            List<Long> ans = new ArrayList<>();
            ArrayDeque<Long> q = new ArrayDeque<>();
            while (true) {
                try {
                    String str = reader.readLine();
                    if (str == null) {
                        break;
                    }
                    String[] line = str.replaceAll("[\\s]{2,}", " ").trim().split(" ");
                    int e = Integer.parseInt(line[0]);
                    if(e==9){
                        break;
                    }
                    long n, first, last;
                    switch (e){
                        case(1):
                            n=Long.parseLong(line[1]);
                            q.addFirst(n);
                            break;
                        case(2):
                            n=Long.parseLong(line[1]);
                            q.addLast(n);
                            break;
                        case(3):
                            first = q.removeFirst();
                            ans.add(first);
                            break;
                        case(4):
                            last = q.removeLast();
                            //writer.
                            ans.add(last);
                            break;
                        case(5):
                            ans.add(q.getFirst());
                            break;
                        case(6):
                            ans.add(q.getLast());
                            break;
                        case(7):
                            ans.add((long)q.size());
                            break;
                        case(8):
                            q.clear();
                            break;
                    }
                } catch (NumberFormatException e) {
                    break;
                }
            }
            for(Long e: ans){
                writer.println(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
