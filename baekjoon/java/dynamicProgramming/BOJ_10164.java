package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10164 {

    private static int N, M, K;
    private static int[] dx = {0, 1};
    private static int[] dy = {1, 0};
    private static int[][] map, cache;
    private static Node pass;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        cache = new int[N + 1][M + 1];
        map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = (i - 1) * M + j;
                if (map[i][j] == K) {
                    pass = new Node(j, i);
                }
            }
        }

        int result;
        if (K == 0) {
            result = dfs(new Node(1, 1), new Node(M, N));
        } else {
            result = dfs(new Node(1, 1), pass) * dfs(pass, new Node(M, N));
        }

        System.out.println(result);
    }

    private static int dfs(Node cur, Node dst) {
        if (cur.x == dst.x && cur.y == dst.y) {
            return 1;
        }

        for (int i = 0; i < 2; i++) {
            Node next = new Node(cur.x + dx[i], cur.y + dy[i]);

            if (isValidRange(next, dst)) continue;
            if (cache[next.y][next.x] != 0) {
                cache[cur.y][cur.x] += cache[next.y][next.x];
            } else {
                cache[cur.y][cur.x] += dfs(next, dst);
            }
        }
        return cache[cur.y][cur.x];
    }

    private static boolean isValidRange(Node pos, Node dst) {
        return !(1 <= pos.x && pos.x <= dst.x && 1 <= pos.y && pos.y <= dst.y);
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
