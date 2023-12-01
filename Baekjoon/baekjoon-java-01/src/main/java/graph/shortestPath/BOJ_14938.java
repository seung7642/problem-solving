package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14938 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, R, ans = -1;
    private static int[][] dist;
    private static int[] item;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        item = new int[N + 1];
        dist = new int[N + 1][N + 1];
        init();

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            item[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dist[u][v] = dist[v][u] = weight;
        }

        floydWarshall();
        print();
        br.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    private static void print() {
        for (int i = 1; i <= N; i++) {
            int cnt = item[i];
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                if (dist[i][j] <= M) cnt += item[j];
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }
    }
}
