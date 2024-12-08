package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16234 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, L, R, ans;
    private static int[][] map;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][N];
            if(!setArea()) break;
            ans++;
        }

        System.out.println(ans);
        br.close();
    }

    private static boolean setArea() {
        boolean isMove = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    List<Node> list = new ArrayList<>();
                    list.add(new Node(j, i));
                    int sum = dfs(new Node(j, i), list);

                    if (list.size() > 1) {
                        updateMap(sum, list);
                        isMove = true; // 인구이동 발생
                    }
                }
            }
        }

        return isMove;
    }

    private static void updateMap(int sum, List<Node> list) {
        int avg = sum / list.size();
        for (Node node : list)
            map[node.y][node.x] = avg;
    }

    private static int dfs(Node start, List<Node> list) {
        visited[start.y][start.x] = true;
        int sum = map[start.y][start.x];

        for (int i = 0; i < 4; i++) {
            int nx = start.x + dx[i];
            int ny = start.y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;

            int diff = Math.abs(map[start.y][start.x] - map[ny][nx]);
            if (diff < L || diff > R) continue;
            if (visited[ny][nx]) continue;

            list.add(new Node(nx, ny));
            sum += dfs(new Node(nx, ny), list);
        }

        return sum;
    }
}
