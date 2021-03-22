package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3035 {

    private static int R, C, ZR, ZC;
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        ZR = Integer.parseInt(st.nextToken());
        ZC = Integer.parseInt(st.nextToken());

        map = new char[R * ZR][C * ZC];
        input(br);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) { // y행 늘리면서 옮기기.
            for (int j = 0; j < ZR; j++) {
                sb.append(map[i]).append("\n");
            }
        }

        System.out.println(sb.toString());
    }

    private static void input(BufferedReader br) throws Exception {
        for (int i = 0; i < R; i++) { // 입력받은 값을 넣음과 동시에 x행 늘리기
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                for (int k = 0; k < ZC; k++) {
                    map[i][j * ZC + k] = str.charAt(j);
                }
            }
        }
    }
}
