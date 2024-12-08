package divideAndConquer;

import java.util.Scanner;

public class BOJ_11778 {

    private static final int MOD = 1_000_000_007;
    private static long[][] unitMatrix = new long[][]{{1, 0}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long m = sc.nextLong();

        long[][] matrix = new long[][]{{1, 1}, {1, 0}};
        long[][] result = powMatrix(matrix, getGcd(n, m));
        long ans = result[0][1] % MOD;
        System.out.println(ans);
    }

    private static long[][] powMatrix(long[][] matrix, long n) {
        if (n == 0) return unitMatrix;
        if (n == 1) return matrix;

        long[][] nMatrix = powMatrix(matrix, n / 2);
        nMatrix = multipleMatrix(nMatrix, nMatrix);
        return n % 2 == 0 ? nMatrix : multipleMatrix(nMatrix, matrix);
    }

    private static long[][] multipleMatrix(long[][] m1, long[][] m2) {
        long[][] matrix = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    matrix[i][j] += (m1[i][k] * m2[k][j]);
                }
                matrix[i][j] %= MOD;
            }
        }
        return matrix;
    }

    private static long getGcd(long a, long b) {
        return b == 0 ? a : getGcd(b, a % b);
    }
}
