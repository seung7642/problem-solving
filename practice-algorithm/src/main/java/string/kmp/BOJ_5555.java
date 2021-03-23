package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_5555 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String pattern = br.readLine();
        int T = Integer.parseInt(br.readLine());

        int ans = 0;
        while (T-- > 0) {
            String origin = br.readLine();
            origin += origin;
            if (kmp(origin, pattern)) {
                ans++;
            }
        }

        System.out.println(ans);
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

    private static boolean kmp(String origin, String pattern) {
        int[] pi = getPi(pattern);
        int j = 0;
        for (int i = 0; i < origin.length(); i++) {
            while (j > 0 && origin.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (origin.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    j = pi[j];
                    return true;
                } else {
                    j++;
                }
            }
        }
        return false;
    }
}
