package graph.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17136 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int[][] map = new int[10][10];
    private static int[] paper = { 0, 5, 5, 5, 5, 5 }; // 각 종류의 색종이는 5개씩 가지고 있다.
    private static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        StringTokenizer st;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, 0);

        System.out.println(ans == Integer.MAX_VALUE ? "-1" : ans);
        br.close();
    }

    public static void dfs(int x, int y, int cnt) {
        if (ans <= cnt) return;
        if (x > 9 && y >= 9) {
            ans = Math.min(ans, cnt);
            return;
        }

        if (x > 9) {
            dfs(0, y + 1, cnt);
            return;
        }

        if (map[y][x] == 1) {
            for (int i = 1; i <= 5; i++) {
                if (paper[i] == 0) continue; // 해당 크기의 색종이가 없을 경우.
                if (isAttachable(x, y, i)) {
                    attach(x, y, i, 0); // 색종이를 붙인다.
                    paper[i]--;
                    dfs(x + i, y, cnt + 1);
                    attach(x, y, i, 1); // 색종이를 다시 떼어낸다.
                    paper[i]++;
                }
            }
        } else { // 오른쪽으로 이동한다.
            dfs(x + 1, y, cnt);
        }
    }

    // (x, y) 좌표를 기준으로 size * size 크기의 색종이를 붙인다. (혹은 떼어낸다.)
    private static void attach(int x, int y, int size, int state) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                map[i][j] = state;
            }
        }
    }

    // (x, y) 좌표를 기준으로 size * size 크기의 색종이를 붙일 수 있는지 확인한다.
    private static boolean isAttachable(int x, int y, int size) {
        for (int i = y; i < y + size; i++) {
            for (int j = x; j < x + size; j++) {
                if (i < 0 || i > 9 || j < 0 || j > 9) return false;
                if (map[i][j] != 1) return false;
            }
        }
        return true;
    }
}