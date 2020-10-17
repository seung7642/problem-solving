package graph.shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2660 {

    private static final int INF = 1000000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans = Integer.MAX_VALUE, memberCnt;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        init();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) break;

            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        floydWarshall();
        print();

        br.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
    }

    private static void print() {
        int[] memberScore = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            int score = 0;
            for (int j = 1; j <= N; j++) {
                score = Math.max(score, arr[i][j]);
            }

            memberScore[i] = score;
            ans = Math.min(ans, score);
        }

        for (int i = 1; i <= N; i++) {
            if (memberScore[i] == ans) memberCnt++;
        }

        System.out.println(ans + " " + memberCnt);

        for (int i = 1; i <= N; i++) {
            if (memberScore[i] == ans) System.out.print(i + " ");
        }

        System.out.println();
    }

    private static void init() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                arr[i][j] = INF;
            }
        }
    }
}
