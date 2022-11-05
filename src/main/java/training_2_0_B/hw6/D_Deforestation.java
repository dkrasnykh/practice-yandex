package training_2_0_B.hw6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;

public class D_Deforestation {
    static long binsearch(long l, long r, long A, long K, long B, long M, long X) {
        BigInteger lbig = BigInteger.valueOf(l);
        BigInteger rbig = BigInteger.valueOf(r);
        while (lbig.compareTo(rbig)<0) {
            BigInteger mbig = (lbig.add(rbig)).divide(BigInteger.valueOf(2));
            if ((((mbig.subtract(mbig.divide(BigInteger.valueOf(K)))).multiply(BigInteger.valueOf(A))).add(mbig.subtract(mbig.divide(BigInteger.valueOf(M))).multiply(BigInteger.valueOf(B)))).compareTo(BigInteger.valueOf(X))>=0){
                rbig = mbig;
            } else {
                lbig = mbig.add(BigInteger.ONE);
            }
        }
        return lbig.longValue();
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            String[] line = reader.readLine().split(" ");
            long A = Long.parseLong(line[0]);
            long K = Long.parseLong(line[1]);
            long B = Long.parseLong(line[2]);
            long M = Long.parseLong(line[3]);
            long X = Long.parseLong(line[4]);
            writer.println(binsearch(0, Long.MAX_VALUE, A, K, B, M, X));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
