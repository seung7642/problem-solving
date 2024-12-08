package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int k, row, col;
    private static int[][] map;
    private static int[][] visited;
    private static int[] dx = {-1, 1, 0, 0, -2, -2, 2, 2, -1, 1, -1, 1};
    private static int[] dy = {0, 0, 1, -1, 1, -1, 1, -1, 2, 2, -2, -2};

    private static class Node {
        int x, y, cnt, time;

        public Node(int x, int y, int cnt, int time) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        visited = new int[row][col];
        map = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;
            }
        }

        System.out.println(bfs());
        br.close();
    }

    private static int bfs() {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0, 0));

        while (!q.isEmpty()) {
            Node now = q.poll();

            if (now.x == col - 1 && now.y == row - 1) {
                return now.time;
            }

            for (int i = 0; i < 12; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (i >= 4 && now.cnt == k) continue; // 말처럼 이동이 불가능하다면
                if (nx < 0 || nx >= col || ny < 0 || ny >= row) continue;
                if (map[ny][nx] == 1) continue;

                int val = (i < 4 ? 0 : 1); // 말처럼 이동하면 1, 아니면 0
                if (visited[ny][nx] == -1 || visited[ny][nx] > now.cnt + val) {
                    visited[ny][nx] = now.cnt + val;
                    q.add(new Node(nx, ny, now.cnt + val, now.time + 1));
                }
            }
        }

        return -1;
    }
}
