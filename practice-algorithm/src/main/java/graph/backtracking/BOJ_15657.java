package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_15657 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] arr;
    private static int[] check;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        check = new int[N];
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);

        dfs(0, 0);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(int start, int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(check[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < N; i++) {
            check[depth] = arr[i];
            dfs(i, depth + 1);
        }
    }
}
