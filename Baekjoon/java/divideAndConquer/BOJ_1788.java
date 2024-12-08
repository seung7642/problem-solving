package divideAndConquer;

import java.util.Scanner;

// 음의 피보나치 수열 구하기. (음의 피보나치 수열은 양의 피보나치 수열과 대칭을 이룬다.)
public class BOJ_1788 {

    private static final int MOD = 1_000_000_000;
    private static long[][] unitMatrix = new long[][]{{1, 0}, {0, 1}};

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();

        if (n == 0) sb.append(0).append("\n");
        else if (n > 0) sb.append(1).append("\n");
        else sb.append(n % 2 == 0 ? -1 : 1).append("\n");

        n = Math.abs(n);

        long[][] matrix = new long[][]{{1, 1}, {1, 0}};
        long[][] result = powMatrix(matrix, n);
        sb.append(result[0][1] % MOD).append("\n");

        System.out.println(sb.toString());
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
