package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_5014 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int F, G, S, U, D;
    private static int[] check;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        check = new int[F + 1];

        System.out.println(bfs(S));
        br.close();
    }

    private static String bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        check[start] = 1;

        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == G) return String.valueOf(check[now] - 1);

            for (int i = 0; i < 2; i++) {
                int next;

                if (i == 0) next = now + U;
                else next = now - D;

                if (next < 1 || next > F) continue;
                if (check[next] != 0) continue;

                q.add(next);
                check[next] = check[now] + 1;
            }
        }

        return "use the stairs";
    }
}
