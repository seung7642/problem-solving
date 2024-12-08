package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_3085 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static char[][] board;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        board = new char[N][N];
        for (int i = 0; i < N; i++) {
            board[i] = in.readLine().toCharArray();
        }

        ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (j + 1 < N) {
                    swap(j, i, 0);
                    consecutiveSubsequence();
                    swap(j, i, 0);
                }
                if (i + 1 < N) {
                    swap(j, i, 1);
                    consecutiveSubsequence();
                    swap(j, i, 1);
                }
            }
        }
        System.out.println(ans);
    }

    private static void swap(int x, int y, int cmd) {
        char tmp = board[y][x];
        if (cmd == 0) { // 좌우 스왑
            board[y][x] = board[y][x + 1];
            board[y][x + 1] = tmp;
        } else if (cmd == 1) { // 상하 스왑
            board[y][x] = board[y + 1][x];
            board[y + 1][x] = tmp;
        }
    }

    private static void consecutiveSubsequence() {
        int max = 0;
        for (int i = 0; i < N; i++) {
            int cnt = 1;
            for (int j = 1; j < N; j++) {
                if (board[i][j] == board[i][j - 1]) cnt++;
                else cnt = 1;
                if (max < cnt) max = cnt;
            }

            cnt = 1;
            for (int j = 1; j < N; j++) {
                if (board[j][i] == board[j - 1][i]) cnt++;
                else cnt = 1;
                if (max < cnt) max = cnt;
            }
        }
        if (ans < max) ans = max;
    }
}