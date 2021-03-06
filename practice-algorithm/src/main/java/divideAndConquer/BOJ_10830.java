package divideAndConquer;

import java.util.Scanner;

public class BOJ_10830 {

    private static int MOD = 1000, N;
    private static int[][] matrix;
    private static int[][] unitMatrix;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        long B = sc.nextLong();

        init(sc);
        matrix = powMatrix(B, matrix);
        print();
    }

    private static void init(Scanner sc) {
        matrix = new int[N][N];
        unitMatrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = sc.nextInt() % MOD;
            }
        }

        for (int i = 0; i < N; i++) {
            unitMatrix[i][i] = 1;
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (int[] m : matrix) {
            for (int i : m) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int[][] powMatrix(long n, int[][] matrix) {
        if (n == 0) return unitMatrix;
        if (n == 1) return matrix;

        int[][] nMatrix = powMatrix(n / 2, matrix);
        nMatrix = multipleMatrix(nMatrix, nMatrix);

        return n % 2 == 0 ? nMatrix : multipleMatrix(nMatrix, matrix);
    }

    private static int[][] multipleMatrix(int[][] m1, int[][] m2) {
        int[][] matrix = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++)
                    matrix[i][j] += (m1[i][k] * m2[k][j]) % MOD;
                matrix[i][j] %= MOD;
            }
        }

        return matrix;
    }
}