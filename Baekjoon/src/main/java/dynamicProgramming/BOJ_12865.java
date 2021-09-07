package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 배낭 문제
public class BOJ_12865 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K; // N:보석의 수, K:배낭에 담을 수 있는 무게
    private static int[][] dp;
    private static Node[] items;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][K + 1];
        items = new Node[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(in.readLine());
            int weight = Integer.parseInt(st.nextToken()); // 무게
            int value = Integer.parseInt(st.nextToken());  // 가치
            items[i] = new Node(weight, value);
        }

        for (int i = 1; i <= N; i++) {
            int weight = items[i].weight; // i번째 보석의 무게
            int value = items[i].value;   // i번째 보석의 가치
            for (int j = 1; j <= K; j++) {
                if (j - weight >= 0) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight] + value);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }

    private static class Node {
        int weight, value;

        public Node(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }
}
