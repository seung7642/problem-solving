package problems_1506;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 최장 공통 부분 문자열 (Longest Common Substring)
public class C {

    private static final int MAX_N = 20 + 1;
    private static String str1, str2;
    private static int[][] cache = new int[MAX_N][MAX_N];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            str1 = br.readLine();
            str2 = br.readLine();
            int result = 0;

            for (int i = 0; i < str1.length(); i++) {
                for (int j = 0; j < str2.length(); j++) {
                    if (str1.charAt(i) == str2.charAt(j)) {
                        if (i == 0 || j == 0) {
                            cache[i][j] = 1;
                        } else {
                            cache[i][j] = cache[i - 1][j - 1] + 1;
                        }
                        result = Math.max(result, cache[i][j]);
                    } else {
                        cache[i][j] = 0;
                    }
                }
            }

            int ans = (str1.length() - result) + (str2.length() - result);
            sb.append(ans).append("\n");
        }

        System.out.println(sb.toString());
    }
}
