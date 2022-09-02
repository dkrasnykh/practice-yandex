package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

class Pair {
    private int a;
    private int b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair pair = (Pair) o;
        return a == pair.a && b == pair.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}

public class Turtles {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(reader.readLine());

            Set<Pair> possibleAnswers = getPossibleAnswers(n);
            Set<Pair> trueAnswers = new HashSet<>();

            for (int i = 0; i < n; i++) {
                String[] ab = reader.readLine().split(" ");
                Pair p = new Pair(Integer.parseInt(ab[0]), Integer.parseInt(ab[1]));
                if (possibleAnswers.contains(p)) {
                    trueAnswers.add(new Pair(Integer.parseInt(ab[0]), Integer.parseInt(ab[1])));
                }
            }
            System.out.println(trueAnswers.size());
        }
    }

    static Set<Pair> getPossibleAnswers(int n) {
        Set<Pair> ans = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            ans.add(new Pair(i - 1, n - i));
        }
        return ans;
    }
}
