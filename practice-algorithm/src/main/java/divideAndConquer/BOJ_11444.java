package divideAndConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * 피보나치 수를 빠르게 구하는 방법에는 다음과 같은 여러 방법이 있다.
 *   1. 재귀 (가장 단순한 방법으로 O(2^N)의 시간 복잡도를 가진다.)
 *   2. 피사노 주기 (나누는 수 M이 10^k 일 경우에만 가능)
 *   3. 행렬 (분할정복)
 *   4. 메모이제이션
 *
 * 해당 코드는 피보나치 수를 행렬을 이용해 풀이한다.
 */
public class BOJ_11444 {

    private static final int MOD = 1_000_000_007;
    private static long N;
    private static long[][] unitMatrix; // 단위 행렬
    private static long[][] matrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextLong();
        init();

        long[][] res = powMatrix(matrix, N - 1);
        System.out.println(N == 1 ? "1" : res[0][0]);
    }

    private static void init() {
        unitMatrix = new long[][]{{1, 0}, {0, 1}};
        matrix = new long[][]{{1, 1}, {1, 0}};
    }

    // 행렬 제곱 (분할 정복)
    private static long[][] powMatrix(long[][] matrix, long n) {
        if (n == 0) return unitMatrix; // 행렬이 0제곱일 때는 단위 행렬을 반환한다.
        if (n == 1) return matrix;

        long[][] nMatrix = powMatrix(matrix, n / 2);
        nMatrix = multipleMatrix(nMatrix, nMatrix);
        return n % 2 == 0 ? nMatrix : multipleMatrix(nMatrix, matrix);
    }

    // 행렬 곱
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
