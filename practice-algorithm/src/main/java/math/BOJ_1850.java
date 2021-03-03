package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1850 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long N1 = Long.parseLong(st.nextToken());
            long N2 = Long.parseLong(st.nextToken());
            long gcdVal = gcd(Math.max(N1, N2), Math.min(N1, N2));

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < gcdVal; i++) {
                sb.append("1");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
