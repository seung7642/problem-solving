package string.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11585 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                sb.append(st.nextToken().charAt(0));
            }
            br.readLine();

            String pattern = sb.toString();

            sb.append(sb);
            sb.deleteCharAt(sb.length() - 1);
            String origin = sb.toString();

            int cnt = kmp(origin, pattern);
            int gcd = gcd(N, cnt);

            cnt /= gcd;
            N /= gcd;

            System.out.println(cnt + "/" + N);
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

    private static int kmp(String origin, String pattern) {
        int[] pi = getPi(pattern);
        int j = 0, cnt = 0;
        for (int i = 0; i < origin.length(); i++) {
            while (j > 0 && (origin.charAt(i) != pattern.charAt(j))) {
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

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
