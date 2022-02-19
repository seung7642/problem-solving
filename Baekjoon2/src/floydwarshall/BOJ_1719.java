package floydwarshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1719 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[][] matrix, path;
    private static StringBuffer sb = new StringBuffer();

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        path = new int[N + 1][N + 1];
        matrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(matrix[i], 10001);
            matrix[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            matrix[u][v] = cost;
            matrix[v][u] = cost;
            path[u][v] = u;
            path[v][u] = v;
        }

        floydwarshall();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) sb.append("- ");
                else sb.append(path[j][i]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void floydwarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (matrix[i][j] > matrix[i][k] + matrix[k][j]) {
                        matrix[i][j] = matrix[i][k] + matrix[k][j];
                        path[i][j] = path[k][j];
                    }
                }
            }
        }
    }
}
