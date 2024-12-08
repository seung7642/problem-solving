package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_16916 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String origin = br.readLine();
            String pattern = br.readLine();

            boolean isSubString = kmp(origin, pattern);
            System.out.println(isSubString ? "1" : "0");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && (pattern.charAt(i) != pattern.charAt(j))) {
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
                if (j == pattern.length() - 1) return true;
                j++;
            }
        }
        return false;
    }
}
