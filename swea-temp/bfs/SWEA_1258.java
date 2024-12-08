package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1258 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, groupCnt;
    private static int[][] matrix;
    private static int[][] visited;
    private static int[] dx = {0, 1};
    private static int[] dy = {1, 0};
    private static Queue<Node> pq = new PriorityQueue<>();
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            matrix = new int[N + 2][N + 2];
            visited = new int[N + 2][N + 2];

            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 1; j <= N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            groupCnt = 0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (visited[i][j] != 0 || matrix[i][j] == 0) continue;
                    bfs(j, i, ++groupCnt);
                }
            }

            sb.append("#").append(tc).append(" ").append(groupCnt).append(" ");
            while (!pq.isEmpty()) {
                Node node = pq.poll();
                sb.append(node.y).append(" ").append(node.x).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs(int x, int y, int groupCnt) {
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(x, y));
        visited[y][x] = groupCnt;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            int checkEndPos = 0;
            for (int i = 0; i < 2; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (isEndPos(nx, ny)) checkEndPos++;
                if (!valid(nx, ny) || visited[ny][nx] != 0 || matrix[ny][nx] == 0) continue;
                visited[ny][nx] = groupCnt;

                q.add(new Node(nx, ny));
            }

            if (checkEndPos == 2) {
                int xLen = cur.x - x + 1;
                int yLen = cur.y - y + 1;
                pq.add(new Node(xLen, yLen, xLen * yLen));
            }
        }
    }

    private static boolean isEndPos(int x, int y) {
        if (x == N || y == N) {
            return true;
        } else if (x != 0 && y != 0 && matrix[y][x] == 0) {
            return true;
        }
        return false;
    }

    private static boolean valid(int x, int y) { return 0 <= x && x < N && 0 <= y && y < N; }

    private static class Node implements Comparable<Node> {
        int x, y, size;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public int compareTo(Node o) {
            if (this.size == o.size) {
                return this.y - o.y;
            }
            return this.size - o.size;
        }
    }
}
