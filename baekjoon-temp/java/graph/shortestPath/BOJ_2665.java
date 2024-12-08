package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_2665 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] map;
    private static int[][] check;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    static class Node implements Comparable<Node> {

        int x, y, cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Node o) {
            return (cnt <= o.cnt ? -1 : 1);
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        init();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j));
                check[i][j] = INF;
            }
        }

        System.out.println( bfs(0, 0) );
        br.close();
    }

    private static int bfs(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, y, 0));
        check[y][x] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.x == N - 1 && cur.y == N - 1) return cur.cnt;

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (map[ny][nx] == 0) { // 검은 방
                    if (check[ny][nx] > check[cur.y][cur.x] + 1) {
                        check[ny][nx] = check[cur.y][cur.x] + 1;
                        pq.add(new Node(nx, ny, cur.cnt + 1));
                    }
                } else {
                    if (check[ny][nx] > check[cur.y][cur.x]) {
                        check[ny][nx] = check[cur.y][cur.x];
                        pq.add(new Node(nx, ny, cur.cnt));
                    }
                }
            }
        }
        return -1;
    }

    private static void init() {
        map = new int[N][N];
        check = new int[N][N];
    }
}
