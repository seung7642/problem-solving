package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_2117 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, max;
    private static int[][] board;
    private static int[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited = new int[N][N];
                    bfs(j, i);
                }
            }
            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y));
        visited[y][x] = 1;

        int K = 1;
        int homeCnt = board[y][x] == 1 ? 1 : 0;
        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (K == visited[cur.y][cur.x]) {
                int serviceCost = K * K + (K - 1) * (K - 1);
                if (serviceCost <= homeCnt * M) {
                    max = Math.max(max, homeCnt);
                }
                K++;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!valid(nx, ny) || visited[ny][nx] != 0) continue;
                if (board[ny][nx] == 1) homeCnt++;
                visited[ny][nx] = visited[cur.y][cur.x] + 1;
                q.add(new Node(nx, ny));
            }
        }
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
