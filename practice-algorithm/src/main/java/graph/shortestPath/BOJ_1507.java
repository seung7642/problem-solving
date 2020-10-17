package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1507 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static int[][] arr, dist;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        dist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        floydWarshall();
        print();

        br.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j || i == k || j == k) continue;

                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        ans = -1;
                        return;
                    }

                    if (dist[i][j] == dist[i][k] + dist[k][j])
                        arr[i][j] = 0;
                }
            }
        }
    }

    private static void print() {
        if (ans == -1) {
            System.out.println(ans);
            return;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (arr[i][j] != 0) {
                    ans += arr[i][j];
                }
            }
        }
        System.out.println(ans);
    }
}
