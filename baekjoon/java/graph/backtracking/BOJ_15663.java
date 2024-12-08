package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_15663 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] arr, ans;
    private static boolean[] visited;
    private static Set<String> set = new LinkedHashSet<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[M];
        visited = new boolean[N];
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        dfs("", 0);

        for (String str : set) {
            sb.append(str).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    private static void dfs(String key, int depth) {
        if (depth == M) {
            set.add(key);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            ans[depth] = arr[i];
            dfs(key + arr[i] + " ",depth + 1);
            visited[i] = false;
        }
    }
}
