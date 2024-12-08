package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11404 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] distance;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        distance = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (i == j) continue;
                distance[i][j] = INF;
            }
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            distance[u][v] = Math.min(distance[u][v], weight);
        }

        floydWarshall();
        print();
        br.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k < N + 1; k++) { // 거쳐가는 정점
            for (int i = 1; i < N + 1; i++) { // 출발지 정점
                for (int j = 1; j < N + 1; j++) { // 목적지 정점
                    distance[i][j] = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                }
            }
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                if (distance[i][j] >= INF) sb.append("0 ");
                else sb.append(distance[i][j] + " ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
