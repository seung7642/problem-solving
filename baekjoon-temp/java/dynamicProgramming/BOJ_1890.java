package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890 {

    private static int N;
    private static int[][] map;
    private static long[][] cache;
    private static int[] dx = {1, 0};
    private static int[] dy = {0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        cache = new long[N + 1][N + 1];
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1);
        System.out.println(cache[1][1]);
    }

    public static boolean dfs(int x, int y) {
        if (map[y][x] == 0) {
            if (x == N && y == N) {
                cache[N][N] = 1;
                return true;
            }
            return false;
        }

        boolean isSearchFinish = false; // 해당 노드를 기준으로 오른쪽, 아래쪽의 탐색을 마쳤는지 체크.
        for (int i = 0; i < dx.length; i++) {
            int nx = (dx[i] == 0 ? x : x + map[y][x]);
            int ny = (dy[i] == 0 ? y : y + map[y][x]);

            if (!isValidRange(nx, ny)) continue;

            // 다음 지점에서 이미 탐색이 진행되었다면
            if (cache[ny][nx] > 0) {
                cache[y][x] += cache[ny][nx]; // 현재 지점에서 도착지까지 이동할 수 있는 경우의 수 추가하기
                isSearchFinish = true;
                continue;
            }

            if (dfs(nx, ny)) {
                cache[y][x] += cache[ny][nx];
                isSearchFinish = true;
            }
        }
        return isSearchFinish;
    }

    private static boolean isValidRange(int x, int y) {
        return (1 <= x && x <= N) && (1 <= y && y <= N);
    }
}
