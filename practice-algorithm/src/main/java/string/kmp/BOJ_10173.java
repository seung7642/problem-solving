package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_10173 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String origin, pattern = "nemo";

        while (!(origin = br.readLine()).equals("EOI")) {
            sb.append(kmp(origin, pattern)).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int[] getPi(String pattern) {
        int[] pi = new int[pattern.length()];
        int j = 0;
        for (int i = 1; i < pattern.length(); i++) {
            char c1 = Character.toLowerCase(pattern.charAt(i));
            char c2 = Character.toLowerCase(pattern.charAt(j));

            while (j > 0 && c1 != c2) {
                j = pi[j - 1];
            }
            if (c1 == c2) {
                pi[i] = ++j;
            }
        }
        return pi;
    }

    private static String kmp(String origin, String pattern) {
        int[] pi = getPi(pattern);
        int j = 0;
        for (int i = 0; i < origin.length(); i++) {
            char c1 = Character.toLowerCase(origin.charAt(i));
            char c2 = Character.toLowerCase(pattern.charAt(j));

            while (j > 0 && c1 != c2) {
                j = pi[j - 1];
            }
            if (c1 == c2) {
                if (j == pattern.length() - 1) {
                    return "Found";
                } else {
                    j++;
                }
            }
        }
        return "Missing";
    }
}
