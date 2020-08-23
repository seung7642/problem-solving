package divideAndConquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2630 {

    private static boolean[][] board;
    private static int blue;
    private static int white;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        board = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < line.length; j++) {
                board[i][j] = line[j].equals("1");
            }
        }

        divide(0, 0, n);

        System.out.println(white + "\n" + blue);
    }

    private static void divide(int x, int y, int n) {
        // 해당 구간의 색이 모두 같은지 확인한다.
        // 또, 같다면 파란색인지 하얀색인지를 확인한다.
        if (isSameColor(x, y, n)) {
            if (board[y][x]) blue++;
            else white++;
            return;
        }

        divide(x, y, n / 2);
        divide(x + n / 2, y, n / 2);
        divide(x, y + n / 2, n / 2);
        divide(x + n / 2, y + n / 2, n / 2);
    }

    private static boolean isSameColor(int x, int y, int n) {
        boolean criteria = board[y][x];

        for (int i = y; i < y + n; i++) {
            for (int j = x; j < x + n; j++) {
                if (board[i][j] != criteria) return false;
            }
        }
        return true;
    }
}
