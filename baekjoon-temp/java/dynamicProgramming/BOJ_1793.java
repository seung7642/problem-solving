package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BOJ_1793 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BigInteger[] dp;

    public static void main(String[] args) throws IOException {
        dp = new BigInteger[251];
        dp[0] = new BigInteger("1");
        dp[1] = new BigInteger("1");
        dp[2] = new BigInteger("3");
        for (int i = 3; i <= 250; i++) {
            dp[i] = dp[i - 2].multiply(new BigInteger("2"));
            dp[i] = dp[i].add(dp[i - 1]);
        }

        String str = "";
        while ((str = in.readLine()) != null) {
            int n = Integer.parseInt(str);
            System.out.println(dp[n]);
        }
    }
}
