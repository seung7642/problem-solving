package graph.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static int[][] map;
    private static int[][] check;
    private static int[] dx = {1, 0, 1};
    private static int[] dy = {0, 1, 1};

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

        dfs(1, 0, 0);
        System.out.println(ans);

        br.close();
    }

    private static void dfs(int x, int y, int pipe) {
        if (x == N - 1 && y == N - 1) {
            ans++;
            return;
        }

        for (int i = 0; i < 3; i++) { // 0: 가로, 1: 세로, 2: 대각선
            if ((i == 0 && pipe == 1) || (i == 1 && pipe == 0)) continue;

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= N || ny >= N || map[ny][nx] == 1) continue;
            if (i == 2 && (map[y][x + 1] == 1 || map[y + 1][x] == 1)) continue;

            dfs(nx, ny, i);
        }
    }
}
