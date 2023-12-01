package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1701 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String str = br.readLine();
            int len = str.length(), max = 0;
            for (int i = 0; i < len; i++) {
                max = Math.max(max, getMaxPi(str.substring(i, len)));
            }

            System.out.println(max);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int getMaxPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0, max = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                j = pi[j - 1];
            }
            if (pattern.charAt(i) == pattern.charAt(j)) {
                max = Math.max(max, pi[i] = ++j);
            }
        }
        return max;
    }
}
