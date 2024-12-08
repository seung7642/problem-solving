package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1799 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] ans = new int[2]; // 체스판 위의 검은색, 흰색 칸 각각에 놓을 수 있는 비숍의 수
    private static int[][] map;
    private static boolean[][] visited, colors;
    private static int[] dx = {-1, 1};
    private static int[] dy = {-1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        colors = new boolean[N][N];
        visited = new boolean[N][N];
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // true: 검은색 칸, false: 하얀색 칸
                colors[i][j] = (i % 2 == 0 && j % 2 == 0) || (i % 2 == 1 && j % 2 == 1);
            }
        }

        dfs(-1, true, 0);  // 체스판 위 검은색 칸에 대한 탐색.
        dfs(-1, false, 0); // 체스판 위 하얀색 칸에 대한 탐색.

        System.out.println(ans[0] + ans[1]);
        br.close();
    }

    private static void dfs(int idx, boolean color, int cnt) {
        for (int i = idx + 1; i < N * N; i++) {
            int x = i % N;
            int y = i / N;

            if (colors[y][x] != color || map[y][x] == 0 || !isSettable(x, y)) continue;
            visited[y][x] = true; // 비숍을 놓은 자리에 방문 처리.
            dfs(i, color, cnt + 1);
            visited[y][x] = false;
        }
        ans[color ? 1 : 0] = Math.max(ans[color ? 1 : 0], cnt);
    }

    // (x, y) 좌표에 비숍을 놓을 수 있는지 확인한다.
    // 해당 좌표 아래 칸에는 아직 탐색 전이라 비숍이 없기 때문에, 좌상, 우상 대각선에 대해서만 탐색한다.
    private static boolean isSettable(int x, int y) {
        for (int i = 0; i < 2; i++) {
            int nx = x;
            int ny = y;
            while (true) {
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) break;
                if (visited[ny][nx]) return false;
                nx += dx[i];
                ny += dy[i];
            }
        }
        return true;
    }
}
