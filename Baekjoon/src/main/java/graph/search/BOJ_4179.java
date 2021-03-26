package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int row, col;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static Queue<Node> fires = new LinkedList<>();
    private static Queue<Node> person = new LinkedList<>();

    private static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        visited = new int[row][col];
        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'F') fires.add(new Node(j, i));
                if (map[i][j] == 'J') {
                    person.add(new Node(j, i));
                    visited[i][j] = 1;
                }
            }
        }

        System.out.println(bfs());
        br.close();
    }

    private static String bfs() {
        int time = 1;
        while (!person.isEmpty()) {
            int len = fires.size();
            for (int t = 0; t < len; t++) {
                Node now = fires.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || nx >= col || ny < 0 || ny >= row) continue;
                    if (map[ny][nx] == '#' || map[ny][nx] == 'F') continue;
                    if (visited[ny][nx] == 2) continue;

                    visited[ny][nx] = 2;
                    fires.add(new Node(nx, ny));
                }
            }

            len = person.size();
            for (int t = 0; t < len; t++) {
                Node now = person.poll();

                if (now.x == 0 || now.x == col - 1 || now.y == 0 || now.y == row - 1) {
                    return String.valueOf(time);
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || nx >= col || ny < 0 || ny >= row) continue;
                    if (map[ny][nx] == '#' || map[ny][nx] == 'F') continue;
                    if (visited[ny][nx] == 2 || visited[ny][nx] == 1) continue;

                    visited[ny][nx] = 1;
                    person.add(new Node(nx, ny));
                }
            }

            time++;
        }

        return "IMPOSSIBLE";
    }
}
