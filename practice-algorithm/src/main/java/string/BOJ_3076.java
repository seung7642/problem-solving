package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3076 {

    private static int R, C, A, B;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());

        map = new char[R * A][C * B];
        input();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < A; j++) {
                sb.append(map[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static void input() {
        for (int i = 0; i < R; i++) {
            boolean check = (i % 2 == 0); // 열의 시작을 블랙으로 할지 화이트로 할지를 결정.
            boolean isBlack;

            for (int j = 0; j < C; j++) {
                if (j % 2 == 0) isBlack = check;
                else isBlack = !check;
                for (int k = 0; k < B; k++) {
                    map[i][j * B + k] = isBlack ? 'X' : '.';
                }
            }
        }
    }
}
