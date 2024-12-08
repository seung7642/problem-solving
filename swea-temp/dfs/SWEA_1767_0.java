package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1767_0 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, maxConnectedCoreCnt, totalCoreCnt, minLineCnt;
    private static int[][] matrix;
    private static int[] coreX, coreY;
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {-1, 1, 0, 0};
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            matrix = new int[N][N];
            coreX = new int[N];
            coreY = new int[N];
            totalCoreCnt = 0;
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                    if (matrix[i][j] == 1) {
                        if (i != 0 && i != N - 1 && j != 0 && j != N - 1) {
                            coreX[totalCoreCnt] = j;
                            coreY[totalCoreCnt++] = i;
                        }
                    }
                }
            }

            maxConnectedCoreCnt = 0;
            minLineCnt = 0x7fffffff;
            solve(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(minLineCnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int depth, int connectedCoreCnt, int lineCnt) {
        if (depth == totalCoreCnt) {
            if (maxConnectedCoreCnt < connectedCoreCnt) {
                maxConnectedCoreCnt = connectedCoreCnt;
                minLineCnt = lineCnt;
            } else if (maxConnectedCoreCnt == connectedCoreCnt) {
                if (lineCnt < minLineCnt) minLineCnt = lineCnt;
            }
            return;
        }

        int x = coreX[depth];
        int y = coreY[depth];
        int tlineCnt = 0;
        for (int dir = 0; dir < 4; dir++) {
            if (isConnectable(x, y, dir)) {
                tlineCnt = setLine(x, y, dir, 1);
                solve(depth + 1, connectedCoreCnt + 1, lineCnt + tlineCnt);
                setLine(x, y, dir, 0);
            }
        }
        solve(depth + 1, connectedCoreCnt, lineCnt);
    }

    private static boolean isConnectable(int x, int y, int dir) {
        while (true) {
            x += dx[dir]; y += dy[dir];
            if (isOutofrange(x, y)) return true;
            if (matrix[y][x] == 1) return false;
        }
    }

    private static int setLine(int x, int y, int dir, int val) {
        int tcnt = 0;
        while (true) {
            x += dx[dir]; y += dy[dir];
            if (isOutofrange(x, y)) break;
            matrix[y][x] = val;
            tcnt++;
        }
        return tcnt;
    }

    private static boolean isOutofrange(int x, int y) {
        return x < 0 || N <= x || y < 0 || N <= y;
    }
}
