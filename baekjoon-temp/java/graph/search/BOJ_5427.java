package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5427 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, w, h;
    private static char[][] map;
    private static int[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    private static Queue<Node> fires;
    private static Queue<Node> person;

    private static class Node {
        int x, y, time = 0;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            fires = new LinkedList<>();
            person = new LinkedList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            visited = new int[h][w];
            map = new char[h][w];
            for (int i = 0; i < h; i++) {
                String str = br.readLine();
                for (int j = 0; j < w; j++) {
                    map[i][j] = str.charAt(j);
                    if (map[i][j] == '*') fires.add(new Node(j, i));
                    if (map[i][j] == '@') {
                        person.add(new Node(j, i));
                        visited[i][j] = 1;
                    }
                }
            }

            System.out.println(bfs());
        }

        br.close();
    }

    private static String bfs() {
        while (!person.isEmpty()) {
            int len = fires.size();
            for (int t = 0; t < len; t++) { // 불을 먼저 탐색한다.
                Node now = fires.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                    if (map[ny][nx] == '#' || map[ny][nx] == '*') continue;
                    if (visited[ny][nx] == 2) continue;

                    visited[ny][nx] = 2; // 불이 방문한 자리는 2, 상근이가 방문한 자리는 1
                    fires.add(new Node(nx, ny));
                }
            }

            len = person.size();
            for (int t = 0; t < len; t++) {
                Node now = person.poll();

                if (now.x == 0 || now.x == w - 1 || now.y == 0 || now.y == h - 1) {
                    return String.valueOf(now.time + 1);
                }

                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];

                    if (nx < 0 || nx >= w || ny < 0 || ny >= h) continue;
                    if (map[ny][nx] == '#' || map[ny][nx] == '*') continue;
                    if (visited[ny][nx] == 1 || visited[ny][nx] == 2) continue;

                    visited[ny][nx] = 1;
                    person.add(new Node(nx, ny, now.time + 1));
                }
            }
        }

        return "IMPOSSIBLE";
    }
}
