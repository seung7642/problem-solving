package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1786 {

    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            String origin = br.readLine();
            String pattern = br.readLine();

            int cnt = kmp(origin, pattern);
            sb.append(cnt).append("\n");
            for (Integer item : list) {
                sb.append(item).append(" ");
            }

            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    // 해당 패턴 문자열에서 prefix==suffix 인 문자의 갯수를 구한다.
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

    private static int kmp(String origin, String pattern) {
        int[] pi = getPi(pattern);
        int cnt = 0, j = 0;
        for (int i = 0; i < origin.length(); i++) {
            while (j > 0 && (origin.charAt(i) != pattern.charAt(j))) {
                j = pi[j - 1]; // 다음 위치에서의 비교를 위한 인덱스 이동.
            }
            if (origin.charAt(i) == pattern.charAt(j)) {
                if (j == pattern.length() - 1) {
                    cnt++;
                    list.add(i - j + 1);
                    j = pi[j];
                } else {
                    j++;
                }
            }
        }
        return cnt;
    }
}
