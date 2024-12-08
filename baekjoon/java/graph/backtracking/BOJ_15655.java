package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15655 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] arr;
    private static int[] ans;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        ans = new int[M];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int num, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(ans[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = num; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            ans[depth] = arr[i];
            dfs(i, depth + 1);
            visited[i] = false;
        }
    }
}
