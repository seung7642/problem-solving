package highScoreKit.dp;

public class C {

    public static void main(String[] args) {
        int m;
        int n;
        int[][] puddles;

        m = 4;
        n = 3;
        puddles = new int[][]{{2, 2}};
        System.out.println(solution(m, n, puddles));
    }

    public static int solution(int m, int n, int[][] puddles) {
        int[][] board = new int[n][m];
        for (int[] puddle : puddles) {
            int x = puddle[0] - 1;
            int y = puddle[1] - 1;
            board[y][x] = -1; // 장애물 설정
        }

        board[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0;
                    continue;
                }
                if (i != 0) {
                    board[i][j] += board[i - 1][j] % 1_000_000_007;
                }
                if (j != 0) {
                    board[i][j] += board[i][j - 1] % 1_000_000_007;
                }
            }
        }

        return board[n - 1][m - 1] % 1_000_000_007;
    }

}
