package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1915 {

    private static int N, M, ans;
    private static int[][] map, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        cache = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = Character.digit(str.charAt(j), 10);
                if (map[i][j] == 1) {
                    cache[i][j] = 1;
                    ans = 1;
                }
            }
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < M; j++) {
                if (map[i][j] == 1) {
                    if (map[i - 1][j - 1] == 1 && map[i - 1][j] == 1 && map[i][j - 1] == 1) {
                        cache[i][j] = Math.min(Math.min(cache[i - 1][j], cache[i][j - 1]), cache[i - 1][j - 1]) + 1;
                        ans = Math.max(ans, cache[i][j]);
                    }
                }
            }
        }

        System.out.println(ans * ans);
    }
}
