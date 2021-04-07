package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11048 {

    private static int N, M;
    private static int[][] map, cache;
    private static int[] dx = {0, 1, 1};
    private static int[] dy = {1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cache = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = -1;
            }
        }

        cache[0][0] = map[0][0];
        bfs(0, 0);
        System.out.println(cache[N - 1][M - 1]);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));

        while (!q.isEmpty()) {
            Node cur = q.poll();
            for (int i = 0; i < 3; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!isValidRange(nx, ny)) continue;

                if (cache[ny][nx] < cache[cur.y][cur.x] + map[ny][nx] || cache[ny][nx] < 0) {
                    cache[ny][nx] = cache[cur.y][cur.x] + map[ny][nx];
                    q.add(new Node(nx, ny));
                }
            }
        }
    }

    private static boolean isValidRange(int x, int y) {
        return (0 <= x && x < M) && (0 <= y && y < N);
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
