package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 문자열 제곱 (서브 문자열 중 반복되는 횟수 찾기)
public class BOJ_4354 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            String str;

            while (!(str = br.readLine()).equals(".")) {
                int max = getMaxKmp(str);
                sb.append(max).append("\n");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    // KMP 알고리즘으로 pi 배열의 최댓값 구하기.
    private static int getMaxKmp(String str) {
        int len = str.length(), j = 0;
        int[] pi = new int[len];

        for (int i = 1; i < len; i++) {
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = pi[j - 1];
            }

            if (str.charAt(i) == str.charAt(j)) {
                pi[i] = ++j;
            }
        }

        return len % (len - pi[len - 1]) == 0 ? len / (len - pi[len - 1]) : 1;
    }
}
