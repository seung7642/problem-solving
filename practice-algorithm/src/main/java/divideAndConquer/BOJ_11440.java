package divideAndConquer;

import java.util.Scanner;

// 0 ~ n 번째 피보나치 수의 제곱을 합한 값.
// 피보나치 수열의 제곱의 합(1~N까지)은 F(N) * F(N + 1) 이다.
public class BOJ_11440 {

    private static final int MOD = 1_000_000_007;
    private static long[][] unitMatrix = new long[][]{{1, 0}, {0, 1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        long[][] matrix = new long[][]{{1, 1}, {1, 0}};
        long[][] res1 = powMatrix(matrix, n);
        long[][] res2 = powMatrix(matrix, n + 1);
        long ans = ((res1[0][1] % MOD) * (res2[0][1] % MOD)) % MOD;
        System.out.println(ans);
    }

    private static long[][] powMatrix(long[][] matrix, long n) {
        if (n == 0) return unitMatrix; // 행렬이 0제곱일 때는 단위 행렬을 반환한다.
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
                    matrix[i][j] += m1[i][k] * m2[k][j];
                }
                matrix[i][j] %= MOD;
            }
        }
        return matrix;
    }
}
