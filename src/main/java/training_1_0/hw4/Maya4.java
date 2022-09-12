package training_1_0.hw4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.regex.Pattern;

public class Maya4 {
    static int convert(int c) {
        if ('a' <= c && c <= 'z') {
            return c - 'a';
        } else if ('A' <= c && c <= 'Z') {
            return c - 'A' + 26;
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        Pattern regex = Pattern.compile(" ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String[] input = regex.split(reader.readLine());
            int nw = Integer.parseInt(input[0]);
            int ns = Integer.parseInt(input[1]);
            int[] word = new int[52];

            for (int i = 0; i < nw; i++) {
                int letter = convert(reader.read());
                word[letter]++;
            }

            int unique = 0;
            for (int c : word) {
                if (c > 0) {
                    unique++;
                }
            }
            reader.readLine();
            //посчитать для первой части слова (для первых nw символов)
            int res = 0;

            int[] seq = new int[52];
            int matchSize = 0;
            ArrayDeque<Integer> buffer = new ArrayDeque<>();
            for (int i = 0; i < nw; i++) {
                int letter = convert(reader.read());
                buffer.addLast(letter);

                boolean beforeEqual = (word[letter] == seq[letter] );
                if (beforeEqual) {
                    matchSize--;
                }
                seq[letter]++;
                boolean afterEqual = (word[letter] == seq[letter]);
                if (afterEqual) {
                    matchSize++;
                }
            }

            if (matchSize == unique) {
                res++;
            }

            //посчитать сколько символов равно
            for (int i = 1; i < ns - nw + 1; i++) {
                //удаляем первую букву  - нужно хранить этот промежуток между первой и последней буквами
                int letter = buffer.removeFirst();
                boolean beforeRemoveFirst = (word[letter] == seq[letter]);
                if (beforeRemoveFirst) {
                    matchSize--;
                }
                seq[letter]--;
                boolean afterRemoveFirst = (word[letter] == seq[letter]);
                if (afterRemoveFirst) {
                    matchSize++;
                }
                //добавляем
                letter = convert(reader.read());
                buffer.addLast(letter);
                boolean beforeAdd = (word[letter] == seq[letter]);
                if (beforeAdd) {
                    matchSize--;
                }
                seq[letter]++;
                boolean afterAdd = (word[letter] == seq[letter]);
                if (afterAdd) {
                    matchSize++;
                }
                if(matchSize == unique){
                    res++;
                }
            }
            System.out.println(res);
        }
    }
}
