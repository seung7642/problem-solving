package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_2117_0 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M, totalHomeCnt, maxK, ans;
    private static int[][] board;
    private static int[] dx = { 1, 1, -1, -1 };
    private static int[] dy = { -1, 1, 1, -1 };
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            totalHomeCnt = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 1) {
                        totalHomeCnt++;
                    }
                }
            }

            maxK = 1;
            while (maxK * maxK + (maxK - 1) * (maxK - 1) <= totalHomeCnt * M) { maxK++; }
            maxK--;

            ans = 1;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    solve(j, i);
                }
            }
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void solve(int x, int y) {
        int homeCnt = board[y][x];
        for (int k = 2; k <= maxK; k++) {
            x--;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < k - 1; j++) {
                    x += dx[i];
                    y += dy[i];
                    if (!valid(x, y)) continue;
                    homeCnt += board[y][x];
                }
            }

            int serviceCost = k * k + (k - 1) * (k - 1);
            if (serviceCost <= homeCnt * M) {
                ans = Math.max(ans, homeCnt);
            }
        }
    }

    private static boolean valid(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
