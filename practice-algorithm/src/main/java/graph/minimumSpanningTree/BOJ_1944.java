package graph.minimumSpanningTree;

import java.io.*;
import java.util.*;

public class BOJ_1944 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, cnt = 1, dis;
    private static PriorityQueue<Robot> pq = new PriorityQueue<>();
    private static Queue<int[]> q = new LinkedList<>();
    static ArrayList<ArrayList<Robot>> graph;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};

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
                if(ch[j] == '1') map[i][j] = -2;
                if(ch[j] == '0') map[i][j] = -1;
                if(ch[j] == 'S' || ch[j] == 'K') {
                    if(ch[j] == 'S') map[i][j] = 0;
                    else map[i][j] = cnt++;

                    q.add(new int[] {i, j});
                }
            }
        }

        // 그래프 초기화
        graph = new ArrayList<>();
        for (int i = 0; i < q.size(); i++) {
            graph.add(new ArrayList<>());
        }

        // 간선 생성
        while(!q.isEmpty()) {
            int[] pos = q.poll();
            bfs(pos[0], pos[1]);
        }

        if (Prim()) bw.write(dis + "\n");
        else bw.write(-1 + "\n");

        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean Prim() {
        boolean[] visit = new boolean[M + 1];
        int cnt = 0;
        pq.add(new Robot(0, 0));
        while(!pq.isEmpty()) {
            Robot robot = pq.poll();

            // 방문했으면 continue
            if(visit[robot.node]) continue;
            visit[robot.node] = true;
            dis += robot.distance;
            if(++cnt == M + 1) {
                return true;
            }

            // 정점과 연결된 모든 정점을 순회
            for(Robot next : graph.get(robot.node)) {
                pq.add(next);
            }
        }
        return false;
    }

    private static void bfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[N][N];
        q.add(new int[] {row, col, 0});

        while(!q.isEmpty()) {
            int[] pos = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = pos[0] + dx[i];
                int ny = pos[1] + dy[i];

                // 맵밖
                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

                // 방문
                if(visited[nx][ny]) continue;

                // 벽
                if(map[nx][ny] == -2) continue;

                // key를 발견했을 때
                if(map[nx][ny] > 0) {
                    graph.get(map[row][col]).add(new Robot(map[nx][ny],pos[2] + 1));
                }

                //방문처리
                visited[nx][ny] = true;
                q.add(new int[] {nx, ny, pos[2] + 1});
            }
        }
    }
}