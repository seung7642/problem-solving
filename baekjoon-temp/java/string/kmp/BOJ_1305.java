package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1305 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int l = Integer.parseInt(br.readLine());
            String str = br.readLine();
            int ans = solution(l, str);

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int solution(int l, String pattern) {
        int[] pi = getPi(pattern);
        int len = pattern.length();
        return l - pi[len - 1]; // (전광판 길이) - (접두사와 접미사가 같은 최대 갯수)
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
}
