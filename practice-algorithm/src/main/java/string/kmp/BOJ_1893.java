package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 시저 암호
public class BOJ_1893 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int T = Integer.parseInt(br.readLine());
            String A, W, S;
            while (T-- > 0) {
                A = br.readLine();
                W = br.readLine();
                S = br.readLine();

                Map<Character, Integer> map = new HashMap<>();
                Map<Integer, Character> rmap = new HashMap<>();
                for (int i = 0; i < A.length(); i++) {
                    map.put(A.charAt(i), i);
                    rmap.put(i, A.charAt(i));
                }

                // 원문 W에 대해 각 알파벳 순서 A만큼 돌린 후 암호문 S와 문자열 매칭을 한다.
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < A.length(); i++) {
                    if (i > 0) {
                        StringBuilder sb = new StringBuilder();
                        for (int j = 0; j < W.length(); j++) {
                            int idx = (map.get(W.charAt(j)) + 1) %  A.length();
                            sb.append(rmap.get(idx));
                        }
                        W = sb.toString();
                    }
                    if (kmp(S, W) == 1) {
                        list.add(i);
                    }
                }

                StringBuilder sb = new StringBuilder();
                if (list.size() == 0) {
                    sb.append("no solution").append("\n");
                } else if (list.size() == 1) {
                    sb.append("unique: ").append(list.get(0)).append("\n");
                } else {
                    sb.append("ambiguous: ");
                    for (int item : list) {
                        sb.append(item).append(" ");
                    }
                    sb.append("\n");
                }

                System.out.print(sb.toString());
            }
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
