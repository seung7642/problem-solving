package graph.shortestPath;

import java.io.*;
import java.util.*;

public class BOJ_2610 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, groupNumber;
    private static int[][] dist;
    private static int[] check;
    private static PriorityQueue<Integer> representation = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        check = new int[N + 1];
        dist = new int[N + 1][N + 1];

        init();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            dist[x][y] = dist[y][x] = 1;
        }

        floydWarshall();

        for (int i = 1; i <= N; i++) {
            if (check[i] != 0) continue;
            groupNumber++;
            dfs(i);
        }

        for (int i = 1; i <= groupNumber; i++) {
            representation.add(getGroupRepresentation(i));
        }

        print();
        br.close();
    }

    private static int getGroupRepresentation(int groupNumber) {
        int min_sum = 100007, sum, representation = 0;

        for (int i = 1; i <= N; i++) {
            if (check[i] != groupNumber) continue;
            sum = 0;
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF) continue;
                sum = Math.max(sum, dist[i][j]);
            }
            if (min_sum > sum) {
                min_sum = sum;
                representation = i;
            }
        }
        return representation;
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF || i == j) continue;
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
    }

    private static void dfs(int start) {
        if (check[start] != 0) return;
        check[start] = groupNumber;

        for (int i = 1; i <= N; i++) {
            if (dist[start][i] == 0 || dist[start][i] == INF) continue;
            dfs(i);
        }
    }

    private static void print() {
        System.out.println(groupNumber);
        while (!representation.isEmpty()) {
            System.out.println(representation.poll());
        }
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