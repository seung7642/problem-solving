package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2225 {

    private static final int MOD = 1_000_000_000;
    private static int N, K;
    private static long[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        cache = new long[K + 1][N + 1]; // [정수의 갯수][만들고자하는 수]

        // 1. i개 수를 선택해서 0을 만드는 경우는 무조건 0을 선택하는 한 가지 방법뿐.
        for (int i = 1; i <= K; i++) {
            cache[i][0] = 1;
        }

        // 2. 1개의 수를 선택해 i라는 수를 만들기 위해선 i라는 수를 선택하는 한 가지 방법뿐.
        for (int i = 0; i <= N; i++) {
            cache[1][i] = 1;
        }

        // 3. 2개의 수를 선택해 i라는 수를 만들기 위해선 0~i 까지의 경우의 수를 가진다.
        for (int i = 1; i <= N; i++) {
            cache[2][i] = i + 1;
        }

        // dp[K][N] = dp[K-1][0] + ... + dp[K-1][N]
        for (int i = 3; i <= K; i++) { // 선택할 수 있는 수의 갯수
            for (int j = 1; j <= N; j++) { // 만들고자하는 수
                for (int k = 0; k <= j; k++) {
                    cache[i][j] += (cache[i - 1][k]) % MOD;
                }
            }
        }

        System.out.println(cache[K][N] % MOD);
    }
}
