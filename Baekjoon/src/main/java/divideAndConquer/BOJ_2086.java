package divideAndConquer;

import java.util.Scanner;

// a ~ b 번째 피보나치 수의 구간 합.
public class BOJ_2086 {

    private static final long MOD = 1_000_000_000;
    private static long[][] unitMatrix = new long[][]{{1, 0}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long[][] matrix = new long[][]{{1, 1}, {1, 0}};
        long[][] res1 = powMatrix(matrix, a + 1);
        long[][] res2 = powMatrix(matrix, b + 2);
        long ans = ((res2[0][1] % MOD) - (res1[0][1] % MOD) + MOD) % MOD;
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
}
