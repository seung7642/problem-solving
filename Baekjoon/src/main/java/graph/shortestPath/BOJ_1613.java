package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1613 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K, S;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N + 1][N + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
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

    private static void print() throws IOException {
        S = Integer.parseInt(br.readLine());
        for (int i = 0; i < S; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (arr[a][b] == 1) System.out.println("-1");
            else if (arr[b][a] == 1) System.out.println("1");
            else System.out.println("0");
        }
    }
}
