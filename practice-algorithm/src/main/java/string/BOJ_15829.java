package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_15829 {

    private static final int M = 1234567891;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            String str = br.readLine();
            long ans = hashing(str);
            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static long hashing(String str) {
        long result = 0, r = 1;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a' + 1; // 알파벳 a는 1
            if (i > 0) {
                r *= 31;
                r %= M;
            }

            result += (val * r) % M;
        }
        return result % M;
    }
}
