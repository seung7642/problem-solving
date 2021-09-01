package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class SWEA_1249 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, ans;
    private static Node[][] board;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            board = new Node[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                String str = in.readLine();
                for (int j = 0; j < N; j++) {
                    int dist = Character.digit(str.charAt(j), 10);
                    board[i][j] = new Node(j, i, dist);
                }
            }

            ans = bfs();
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int bfs() {
        Queue<Node> q = new PriorityQueue<>();
        q.add(board[0][0]);
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == N - 1 && cur.y == N - 1) {
                return cur.dist;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (!valid(nx, ny) || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                q.add(new Node(nx, ny, cur.dist + board[ny][nx].dist));
            }
        }
        return 0;
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static class Node implements Comparable<Node> {
        int x, y, dist;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}
