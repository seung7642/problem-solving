package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1261 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static class Node implements Comparable<Node> {
        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return cnt - o.cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1][M + 1];
        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            String str = br.readLine();
            for (int j = 1; j <= M; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j - 1));
            }
        }

        System.out.println( bfs(1, 1) );
        br.close();
    }

    private static int bfs(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // 벽을 부순 갯수를 오름차순으로 정렬한다.
        pq.add(new Node(x, y, 0));
        visited[y][x] = true;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == M && cur.y == N)
                return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 1 || nx > M || ny < 1 || ny > N) continue;

                if (!visited[ny][nx] && map[ny][nx] == 0) {
                    visited[ny][nx] = true;
                    pq.add(new Node(nx, ny, cur.cnt));
                }
                if (!visited[ny][nx] && map[ny][nx] == 1) {
                    visited[ny][nx] = true;
                    pq.add(new Node(nx, ny, cur.cnt + 1));
                }
            }
        }

        return 0;
    }
}
