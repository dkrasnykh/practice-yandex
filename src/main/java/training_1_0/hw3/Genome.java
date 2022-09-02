package training_1_0.hw3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Genome {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String genome1 = reader.readLine();
            String genome2 = reader.readLine();
            if(genome1.length()<2 || genome1.length()<2){
                System.out.println(0);
                return;
            }
            Set<String> basisGenome2 = new HashSet<>();
            for (int i = 0; i < genome2.length() - 1; i++) {
                basisGenome2.add(genome2.substring(i, i + 2));
            }
            int closeness = 0;
            for (int i = 0; i < genome1.length() - 1; i++) {
                String base = genome1.substring(i, i + 2);
                if (basisGenome2.contains(base)) {
                    closeness++;
                }
            }
            System.out.println(closeness);
        }
    }
}
