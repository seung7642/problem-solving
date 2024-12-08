package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_4012 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, ans;
    private static int[][] matrix;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(in.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(in.readLine());
            matrix = new int[N][N];
            visited = new boolean[N];
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine());
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0x7fffffff;
            combination(0, 0);
            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static void combination(int depth, int start) {
        if (depth == N / 2) {
            check();
            return;
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            combination(depth + 1, i + 1);
            visited[i] = false;
        }
    }

    private static void check() {
        int A, B;
        A = B = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visited[i] && visited[j]) {
                    A += matrix[i][j] + matrix[j][i];
                } else if (!visited[i] && !visited[j]) {
                    B += matrix[i][j] + matrix[j][i];
                }
            }
        }
        ans = Math.min(ans, Math.abs(A - B));
    }
}
