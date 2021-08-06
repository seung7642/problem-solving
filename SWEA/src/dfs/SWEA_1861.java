package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1861 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, maxRoomCnt, roomNum;
    static int[][] board;
    static int[][] visited;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            visited = new int[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            maxRoomCnt = 0;
            roomNum = Integer.MAX_VALUE;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    visited[i][j] = Math.max(visited[i][j], 1);
                    int result = dfs(j, i, 1);
                    if (maxRoomCnt <= result) {
                        if (maxRoomCnt == result) { roomNum = Math.min(roomNum, board[i][j]); }
                        else { maxRoomCnt = result; roomNum = board[i][j]; }
                    }
                }
            }

            sb.append("#").append(tc).append(" ")
                    .append(roomNum).append(" ").append(maxRoomCnt).append("\n");
        }

        System.out.println(sb);
    }

    private static int dfs(int x, int y, int depth) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isValid(nx, ny)) continue;
            if (visited[ny][nx] > depth + 1) continue;

            if (board[ny][nx] == board[y][x] + 1) {
                visited[ny][nx] = depth + 1;
                depth = dfs(nx, ny, depth + 1);
            }
        }

        return depth;
    }

    private static boolean isValid(int x, int y) { return 0 <= x && x < N && 0 <= y && y < N; }
}
