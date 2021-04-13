package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {
    
    private static int N;
    private static String num;

    public static void main(String[] args) throws IOException {
        num = new BufferedReader(new InputStreamReader(System.in)).readLine();
        N = num.length();
        long[] cache = new long[N + 1]; 
        cache[0] = cache[1] = 1;

        if (isValidCode()) {
            for (int i = 2; i <= N; i++) {
                int tmp = Integer.parseInt(num.charAt(i - 1) + "");
                if (tmp > 0) cache[i] = cache[i - 1] % 1000000;

                tmp += Integer.parseInt(num.charAt(i - 2) + "") * 10;
                if (10 <= tmp && tmp <= 26) cache[i] = (cache[i] + cache[i - 2]) % 1000000;
            }
            System.out.print(cache[N]);
        } else {
            System.out.println("0");
        }
    }
    
    private static boolean isValidCode() {
        if (num.charAt(0) == '0') {
            return false;
        } else if (num.charAt(N - 1) == '0') {
            int val = Character.digit(num.charAt(N - 2), 10);
            if (val > 2) return false;
        }
        return true;
    }
}