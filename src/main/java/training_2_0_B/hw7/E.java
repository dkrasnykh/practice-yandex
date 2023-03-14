package training_2_0_B.hw7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

public class E {

    public static void main(String[] args) {
        //без восстановления ответа
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter writer = new PrintWriter(System.out)) {
            int n = Integer.parseInt(reader.readLine());
            int[][] dp = new int[n + 1][n + 1];
            Arrays.fill(dp[0], 1);
            Arrays.fill(dp[1], 1);
            for(int i =2; i<=n; i++){
                dp[i][0]=1;
                dp[i][1]=1;
            }
            for(int i = 2; i<=n; i++){
                for(int j = 2; j<=n; j++){
                    if(i==j){
                        dp[i][j]=1+dp[i-1][j];
                    } else if (j<i){
                        dp[i][j]=dp[j][j];
                    } else {
                        dp[i][j]=dp[i][j-i]+dp[i-1][j];
                    }
                }
            }
            writer.println(dp[n][n]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
