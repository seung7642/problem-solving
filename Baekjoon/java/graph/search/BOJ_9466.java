package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_9466 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int[] edge;
    private static int[] check;
    private static int[] firstParent;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            firstParent = new int[n + 1];
            check = new int[n + 1];
            edge = new int[n + 1];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++)
                edge[j] = Integer.parseInt(st.nextToken());

            int count = 0;
            for (int i = 1; i <= n; i++) {
                if (check[edge[i]] == 0)
                    count += dfs(i, i, 1);
            }

            System.out.println(n - count);
        }

        br.close();
    }

    private static int dfs(int start, int current, int depth) {
        check[current] = depth;
        firstParent[current] = start;

        int next = edge[current];

        if (check[next] != 0) {
            if (start == firstParent[next]) return depth - check[next] + 1;
            else return 0;
        }

        return dfs(start, next, depth + 1);
    }
}
