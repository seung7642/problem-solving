package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1956 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int V, E;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        arr = new int[V + 1][V + 1];
        init();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a][b] = c;
        }

        floydWarshall();
        print();

        br.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) continue;
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
    }

    private static void print() {
        int ans = INF;
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) continue;
                if (arr[i][j] != INF && arr[j][i] != INF) {
                    ans = Math.min(ans, arr[i][j] + arr[j][i]);
                }
            }
        }
        System.out.println(ans == INF ? "-1" : ans);
    }

    private static void init() {
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j<= V; j++) {
                if (i == j) continue;
                arr[i][j] = INF;
            }
        }
    }
}
