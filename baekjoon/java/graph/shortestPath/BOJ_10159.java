package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10159 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
        }

        floydWarshall();
        print();

        br.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (arr[i][k] + arr[k][j] == 2)
                        arr[i][j] = 1;
                }
            }
        }
    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            int ans = 0;
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (arr[i][j] == 0 && arr[j][i] == 0) ans++;
            }
            System.out.println(ans);
        }
    }
}
