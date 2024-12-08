package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1182 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S, ans;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        dfs(0, 0);
        if (S == 0) ans--; // S가 0일 경우, 공집할에 대해서도 카운트가 되기 때문에 하나 빼준다.

        System.out.println(ans);
        br.close();
    }

    private static void dfs(int idx, int sum) {
        if (idx == N) {
            if (sum == S) ans++;
            return;
        }

        dfs(idx + 1, sum); // 해당 원소를 선택 X
        dfs(idx + 1, sum + arr[idx]); // 해당 원소를 선택 O
    }
}
