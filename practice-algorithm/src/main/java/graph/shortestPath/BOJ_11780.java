package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11780 {

    private static final int INF = 1000000000;
    private static final int NIL = -1;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] dist, next;
    private static Stack<Integer> path;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        next = new int[N + 1][N + 1];
        dist = new int[N + 1][N + 1];
        init();

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dist[u][v] = Math.min(dist[u][v], weight);
            next[u][v] = u;
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
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[k][j];
                    }
                }
            }
        }
    }

    public static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (dist[i][j] == INF) sb.append("0 ");
                else sb.append(dist[i][j]).append(" ");
            }
            sb.append("\n");
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (next[i][j] == NIL) sb.append("0");
                else {
                    path = new Stack<>();
                    int pre = j;
                    path.add(j); // 거쳐가는 정점
                    while (i != next[i][pre]) { // 도착 정점까지
                        pre = next[i][pre];
                        path.push(pre);
                    }
                    // 최단 거리 경로 크기 (출발 정점까지 포함)
                    sb.append((path.size() + 1)).append(" ");
                    sb.append(i).append(" ");
                    while (!path.empty()) {
                        sb.append(path.pop()).append(" ");
                    }
                }
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                next[i][j] = NIL;
                if (i == j) continue;
                dist[i][j] = INF;
            }
        }
    }
}
