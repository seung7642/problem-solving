package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N-Queen 문제 (백트래킹의 대표 문제)
public class SWEA_2806 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, cnt;
    private static boolean[][] board;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());
            board = new boolean[N + 1][N];

            cnt = 0;
            backtracking(0, 0, 0);
            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    private static void backtracking(int x, int y, int depth) {
        if (!isPromising(x, y, depth)) {
            return;
        } else if (depth == N) {
            cnt++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[y + 1][i] = true;
            backtracking(i, y + 1, depth + 1);
            board[y + 1][i] = false;
        }
    }

    private static boolean isPromising(int x, int y, int depth) {
        for (int i = 1; i < depth; i++) {
            if (board[i][x]) { // 1 depth까지 같은 열에 놓인 퀸이 있는지 확인한다.
                return false;
            }
            if (x - i >= 0) {
                if (board[y - i][x - i]) return false;
            }
            if (x + i < N) {
                if (board[y - i][x + i]) return false;
            }
        }
        return true;
    }
}
