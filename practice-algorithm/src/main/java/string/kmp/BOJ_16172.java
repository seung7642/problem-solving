package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16172 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String origin = br.readLine();
            String pattern = br.readLine();
            int ans = kmp(origin, pattern);
            System.out.println(ans > 0 ? "1" : "0");
        } catch (Exception ex) {
            ex.getMessage();
        }
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
            if (Character.isDigit(origin.charAt(i))) continue;

            while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (origin.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    cnt++;
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
}
