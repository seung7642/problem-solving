package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_1242 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int T, N, M;
    static int[][] board;
    static StringBuilder sb = new StringBuilder();

    static int[][] hcode = {
            {0, 0, 0, 0}, // 0
            {0, 0, 0, 1}, // 1
            {0, 0, 1, 0}, // 2
            {0, 0, 1, 1}, // 3
            {0, 1, 0, 0}, // 4
            {0, 1, 0, 1}, // 5
            {0, 1, 1, 0}, // 6
            {0, 1, 1, 1}, // 7
            {1, 0, 0, 0}, // 8
            {1, 0, 0, 1}, // 9
            {1, 0, 1, 0}, // A
            {1, 0, 1, 1}, // B
            {1, 1, 0, 0}, // C
            {1, 1, 0, 1}, // D
            {1, 1, 1, 0}, // E
            {1, 1, 1, 1}, // F
    };

    static int[][][] rate = new int[5][5][5];

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            board = new int[N][M * 4];

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                for (int j = 0; j < M; j++) {
                    char c = str.charAt(j);
                    int num = Character.digit(c, 16);
                    for (int k = 0; k < 4; k++) {
                        board[i][j * 4 + k] = hcode[num][k];
                    }
                }
            }

            init();

            int ans = 0, codeLen = 7;
            int[] codes = new int[8]; // 암호코드 8개의 숫자를 담을 공간.
            for (int i = 1; i < N; i++) {
                for (int j = M * 4 - 1; j >= 0; j--) {
                    if (board[i][j] == 1 && board[i - 1][j] == 0) { // 암호코드 우측 최상단
                        int x, y, z;
                        x = y = z = 0;

                        while (board[i][j] == 1) { z++; j--; }
                        while (board[i][j] == 0) { y++; j--; }
                        while (board[i][j] == 1) { x++; j--; }
                        if (codeLen != 0) { while (board[i][j] == 0) { j--; } }
                        j++;

                        int min = Math.min(Math.min(x, y), z); // 1 비율의 값을 구한다.

                        x /= min; y /= min; z /= min; // 암호코드의 비율을 1배수로 만든다.
                        codes[codeLen--] = rate[x][y][z];

                        if (codeLen == -1) {
                            int checkVal = (codes[0] + codes[2] + codes[4] + codes[6]) * 3 + codes[1] + codes[3] + codes[5] + codes[7];
                            if (checkVal % 10 == 0) { for (int item : codes) ans += item; }
                            codeLen = 7;
                        }
                    }
                }
            }

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void init() {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                for (int k = 0; k < 5; k++)
                    rate[i][j][k] = -1;

        rate[2][1][1] = 0;
        rate[2][2][1] = 1;
        rate[1][2][2] = 2;
        rate[4][1][1] = 3;
        rate[1][3][2] = 4;
        rate[2][3][1] = 5;
        rate[1][1][4] = 6;
        rate[3][1][2] = 7;
        rate[2][1][3] = 8;
        rate[1][1][2] = 9;
    }
}
