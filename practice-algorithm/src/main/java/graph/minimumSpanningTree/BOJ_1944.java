package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_1944 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, cnt = 1, dis;
    private static Queue<Robot> pq = new PriorityQueue<>();
    private static Queue<Point> q = new LinkedList<>();
    private static List<List<Robot>> graph;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

    static class Point {
        int x, y, distance;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static class Robot implements Comparable<Robot> {
        int node, distance;

        public Robot(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }

        @Override
        public int compareTo(Robot o) {
            return distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            char[] ch = br.readLine().toCharArray();
            for (int j = 0; j < N; j++) {
                if (ch[j] == '1') map[i][j] = -2;
                if (ch[j] == '0') map[i][j] = -1;
                if (ch[j] == 'S' || ch[j] == 'K') {
                    if (ch[j] == 'S') map[i][j] = 0;
                    else map[i][j] = cnt++;

                    q.add(new Point(j, i)); // 로봇 | 열쇠라면 큐에 위치를 담는다.
                }
            }
        }

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i < q.size(); i++)
            graph.add(new ArrayList<>());

        // 간선 생성
        while (!q.isEmpty()) {
            Point pos = q.poll();
            bfs(pos.x, pos.y);
        }

        if (prim()) bw.write(dis + "\n");
        else bw.write(-1 + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean prim() {
        boolean[] visit = new boolean[M + 1];
        int cnt = 0;
        pq.add(new Robot(0, 0));

        while (!pq.isEmpty()) {
            Robot robot = pq.poll();

            // 방문했으면 continue
            if (visit[robot.node]) continue;
            visit[robot.node] = true;
            dis += robot.distance;

            if (++cnt == M + 1) return true;

            // 정점과 연결된 모든 정점을 순회
            for (Robot next : graph.get(robot.node))
                pq.add(next);
        }
        return false;
    }

    private static void bfs(int x, int y) {
        Queue<Point> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.add(new Point(x, y, 0));

        while (!q.isEmpty()) {
            Point pos = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos.x + dx[i];
                int ny = pos.y + dy[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[ny][nx] && map[ny][nx] != -2) {
                        if (map[ny][nx] > 0) // 열쇠를 발견했다면
                            graph.get(map[y][x])
                                    .add(new Robot(map[ny][nx],pos.distance + 1));

                        visited[ny][nx] = true;
                        q.add(new Point(nx, ny, pos.distance + 1));
                    }
                }
            }
        }
    }
}