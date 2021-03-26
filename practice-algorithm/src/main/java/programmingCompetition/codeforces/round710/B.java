package programmingCompetition.codeforces.round710;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B {

    private static int N, K, start, end;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            String str = br.readLine();
            setFirstLastChar(str);

            int[] dp = new int[N];
            dp[start] = 1;
            for (int i = start + 1; i <= end; i++) {
                dp[i] = Integer.MAX_VALUE;
            }

            for (int i = start; i < end; i++) {
                if (str.charAt(i) != '*') continue;

                for (int j = i + 1; j <= i + K; j++) {
                    if (j > end) break;
                    if (str.charAt(j) == '*') {
                        dp[j] = Math.min(dp[j], dp[i] + 1);
                    }
                }
            }

            sb.append(dp[end]).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static void setFirstLastChar(String str) {
        int left = 0, right = str.length() - 1;
        boolean findStart = false, findEnd = false;
        while (true) {
            if (str.charAt(left) == '*') {
                start = left;
                findStart = true;
            } else {
                left++;
            }

            if (str.charAt(right) == '*') {
                end = right;
                findEnd = true;
            } else {
                right--;
            }

            if (findStart && findEnd) {
                break;
            }
        }
    }
}
