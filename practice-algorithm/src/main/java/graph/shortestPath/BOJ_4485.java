package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_4485 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, idx = 1;
    private static int[][] map;
    private static int[][] dist;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    static class Node implements Comparable<Node> {

        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return (distance <= o.distance ? -1 : 1);
        }
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            init();

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = INF;
                }
            }

            dijkstra(0, 0);
            System.out.println("Problem " + (idx++) + ": " + dist[N - 1][N - 1]);
        }

        br.close();
    }

    private static void dijkstra(int x, int y) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(x, y, map[y][x]));
        dist[y][x] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                if (dist[ny][nx] > cur.distance + map[ny][nx]) {
                    dist[ny][nx] = cur.distance + map[ny][nx];
                    pq.add(new Node(nx, ny, dist[ny][nx]));
                }
            }
        }
    }

    private static void init() {
        dist = new int[N][N];
        map = new int[N][N];
    }
}
