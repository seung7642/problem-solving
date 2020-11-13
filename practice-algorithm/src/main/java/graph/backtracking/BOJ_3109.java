package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_3109 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int row, col, ans;
    private static char[][] map;
    private static int[] direction = {-1, 0, 1};

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char[row][col];
        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < col; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < row; i++) {
            dfs(0, i);
        }

        System.out.println(ans);
        br.close();
    }

    private static boolean dfs(int x, int y) {
        if (x == col - 1) {
            ans++;
            return true;
        }

        for (int i = 0; i < 3; i++) {
            int nx = x + 1;
            int ny = y + direction[i];

            if (nx < 0 || nx >= col || ny < 0 || ny >= row) continue;
            if (map[ny][nx] == 'x') continue;

            map[ny][nx] = 'x'; // 방문했음을 표시한다.

            // 원웅이의 빵집과 파이프라인을 연결했다면, 해당 루프를 종료한다.
            // 루프를 종료하지않으면 파이프라인 연결에 대한 중복 카운트가 된다.
            if (dfs(nx, ny)) return true;
        }
        return false;
    }
}
