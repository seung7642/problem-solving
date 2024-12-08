package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1937 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static int[][] map;
    private static int[][] check;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        check = new int[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans = Math.max(ans, dfs(j, i));
            }
        }

        System.out.println(ans);
        br.close();
    }

    private static int dfs(int x, int y) {
        if (check[y][x] != 0) return check[y][x];

        int day = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
            if (map[ny][nx] <= map[y][x]) continue;

            day = Math.max(day, dfs(nx, ny) + 1);
            check[y][x] = day;
        }

        return day;
    }
}
