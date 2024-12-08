package graph.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dfs("", 0);

        System.out.println(sb);
        br.close();
    }

    private static void dfs(String str, int depth) {
        if (depth == N) {
            sb.append(str).append("\n");
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (isPrime(Integer.parseInt(str + i)))
                dfs(str + i, depth + 1);
        }
    }

    private static boolean isPrime(int num) {
        if (num == 1) return false;

        int sqrt = (int) Math.sqrt(num);
        for (int i = 2; i <= sqrt; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }
}
