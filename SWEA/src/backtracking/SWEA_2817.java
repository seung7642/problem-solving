package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 부분 수열의 합 (백트래킹의 대표 문제 중 하나)
public class SWEA_2817 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, K, cnt;
    private static int[] arr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            arr = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            cnt = 0;
            backtracking(0, 0);
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void backtracking(int sum, int depth) {
        if (depth == N) {
            if (sum == K) cnt++;
            return;
        }

        backtracking(sum + arr[depth], depth + 1);
        backtracking(sum, depth + 1);
    }
}
