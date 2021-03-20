package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10769 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String pattern1 = ":-)";
        String pattern2 = ":-(";

        int cnt1 = kmp(origin, pattern1);
        int cnt2 = kmp(origin, pattern2);

        StringBuilder sb = new StringBuilder();
        if (cnt1 == 0 && cnt2 == 0) sb.append("none");
        else if (cnt1 == cnt2) sb.append("unsure");
        else if (cnt1 > cnt2) sb.append("happy");
        else sb.append("sad");

        System.out.println(sb.toString());
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
                if (j == pattern.length() - 1) {
                    cnt++;
                    j = 0;
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
}
