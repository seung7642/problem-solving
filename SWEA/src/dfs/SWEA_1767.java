package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1767 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, coreCnt, maxCoreCnt, totalWireLen;
    private static int[][] board;
    private static int[] coreX, coreY;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            board = new int[N][N];
            coreX = new int[N];
            coreY = new int[N];
            coreCnt = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                            coreX[coreCnt] = j;
                            coreY[coreCnt++] = i;
                        }
                    }
                }
            }

            totalWireLen = 0x7fffffff;
            maxCoreCnt = 0;
            dfs(0, 0);
            sb.append("#").append(tc).append(" ").append(totalWireLen).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int depth, int cnt) {
        if (depth == coreCnt) {
            if (maxCoreCnt < cnt) {
                maxCoreCnt = cnt;
                totalWireLen = getWireLen();
            } else if (maxCoreCnt == cnt) {
                totalWireLen = Math.min(totalWireLen, getWireLen());
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            int x = coreX[depth];
            int y = coreY[depth];
            board[y][x] = 0;
            int result = search(board, x, y, i);
            board[y][x] = 1;
            if (result == 2) result = 1;
            dfs(depth + 1, cnt + result);
            if (result == 1) {
                while (true) {
                    x += dx[i];
                    y += dy[i];
                    if (!valid(x, y)) break;
                    board[y][x] = 0;
                }
            }
        }
    }

    private static int getWireLen() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 2) cnt++;
            }
        }
        return cnt;
    }

    private static int search(int[][] board, int x, int y, int dir) {
        if (board[y][x] != 0) {
            return 0;
        } else if (x == 0 || x == N - 1 || y == 0 || y == N - 1) {
            board[y][x] = 2;
            return 2;
        }
        return board[y][x] = search(board, x + dx[dir], y + dy[dir], dir);
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
