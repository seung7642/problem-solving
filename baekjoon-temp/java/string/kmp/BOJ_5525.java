package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5525 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            String origin = br.readLine();
            String pattern = getPattern(N);

            int ans = kmp(origin, pattern);
            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static String getPattern(int n) {
        StringBuilder sb = new StringBuilder("I");
        for (int i = 0; i < n; i++) {
            sb.append("OI");
        }
        return sb.toString();
    }

    private static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    private static int kmp(String origin, String pattern) {
        int[] pi = getPi(pattern);
        int j = 0, cnt = 0;
        for (int i = 0; i < origin.length(); i++) {
            while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (origin.charAt(i) == pattern.charAt(j)) {
                if (j == (pattern.length() - 1)) {
                    j = pi[j];
                    cnt++;
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
}
