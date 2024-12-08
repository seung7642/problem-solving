package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14500 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, maxSum;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(j, i, 0, 0);
                shapeSearch(j, i);
                visited[i][j] = false;
            }
        }

        System.out.println(maxSum);
    }

    private static void shapeSearch(int x, int y) {
        int sum = board[y][x], cnt = 0, minVal = 1000;
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (!isValid(nx, ny)) continue;
            nums[cnt++] = board[ny][nx];
            minVal = Math.min(minVal, board[ny][nx]);
        }

        if (cnt >= 3) {
            for (int num : nums) { sum += num; }
            if (cnt == 4) { sum -= minVal; }
            maxSum = Math.max(maxSum, sum);
        }
    }

    private static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isValid(nx, ny) || visited[ny][nx]) continue;

            visited[ny][nx] = true;
            dfs(nx, ny, depth + 1, sum + board[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    private static boolean isValid(int x, int y) { return (0 <= x && x < M && 0 <= y && y < N); }
}
