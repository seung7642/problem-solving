package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_15665 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] arr;
    private static Set<String> set = new LinkedHashSet<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(arr);
        dfs("", 0);

        for (String s : set) {
            sb.append(s).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    private static void dfs(String str, int depth) {
        if (depth == M) {
            set.add(str);
            return;
        }

        for (int i = 0; i < N; i++) {
            dfs(str + arr[i] + " ", depth + 1);
        }
    }
}
