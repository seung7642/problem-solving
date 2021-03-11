package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10266 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            int[] arr1 = new int[720000];
            int[] arr2 = new int[360000];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                arr1[val] = 1;
                arr1[val + 360000] = 1;
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int val = Integer.parseInt(st.nextToken());
                arr2[val] = 1;
            }

            String ans = kmp(arr1, arr2);
            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int[] failure(int[] pattern) {
        int[] pi = new int[pattern.length];
        int j = 0;
        for (int i = 1; i < pattern.length; i++) {
            while (j > 0 && pattern[i] != pattern[j]) {
                j = pi[j - 1];
            }

            if (pattern[i] == pattern[j]) {
                pi[i] = ++j;
            }
        }

        return pi;
    }

    private static String kmp(int[] origin, int[] pattern) {
        int[] pi = failure(pattern);
        int j = 0;
        for (int i = 0; i < origin.length; i++) {
            while (j > 0 && origin[i] != pattern[j]) {
                j = pi[j - 1];
            }
            if (origin[i] == pattern[j]) {
                if (j == pattern.length - 1) {
                    return "possible";
                } else {
                    j++;
                }
            }
        }

        return "impossible";
    }
}
