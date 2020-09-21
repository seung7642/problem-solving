package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ans;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') {
                    visited = new boolean[N][M];
                    ans = Math.max(ans, bfs(j, i));
                }
            }
        }

        System.out.println(ans);
        br.close();
    }

    private static int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, 0));
        visited[y][x] = true;

        int distance = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();
            distance = Math.max(distance, node.distance);

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if (map[ny][nx] != 'L') continue;
                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new Node(nx, ny, node.distance + 1));
            }
        }

        return distance;
    }
}
