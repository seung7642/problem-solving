package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2458 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ans;
    private static boolean[][] arr, reverseArr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new boolean[N + 1][N + 1];
        reverseArr = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = true;
            reverseArr[b][a] = true;
        }

        floydWarshall(arr);
        floydWarshall(reverseArr);

        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                arr[i][j] |= reverseArr[i][j];
            }
        }

        for (int i = 1; i < N + 1; i++) {
            boolean isComp = true;
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                if (!arr[i][j]) {
                    isComp = false;
                    continue;
                }
            }

            if (isComp) ans++;
        }

        System.out.println(ans);
        br.close();
    }

    private static void floydWarshall(boolean[][] arr) {
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (arr[i][k] && arr[k][j])
                        arr[i][j] = true;
                }
            }
        }
    }
}
