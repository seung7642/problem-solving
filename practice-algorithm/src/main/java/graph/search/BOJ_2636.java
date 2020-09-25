package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, time, lastCheeseNum;
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
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true) {
            visited = new boolean[N][M];
            bfs(new Node(0, 0));
            time++;

            if (!hasCheese()) break;

            meltCheese();
            lastCheeseNum = 0;
        }

        System.out.println(time + "\n" + lastCheeseNum);
        br.close();
    }

    private static void bfs(Node start) {
        Queue<Node> q = new LinkedList<>();
        q.add(start);
        visited[start.y][start.x] = true;

        while (!q.isEmpty()) {
            Node now = q.poll(); // 43번째에서 에러

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N) continue;
                if (visited[ny][nx] || map[ny][nx] == 2) continue;
                visited[ny][nx] = true;

                if (map[ny][nx] == 1) { // 가장 처음(공기와 맞닿은) 치즈를 만났다면
                    map[ny][nx] = 2; // 바로 0으로 만들지 말고 일단 임시 값 2로 수정.
                    lastCheeseNum++;
                    continue;
                }

                q.add(new Node(nx, ny));
            }
        }
    }

    private static boolean hasCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) return true;
            }
        }
        return false;
    }

    private static void meltCheese() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 2) map[i][j] = 0;
            }
        }
    }
}
