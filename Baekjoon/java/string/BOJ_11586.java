package string;

import java.util.Scanner;

public class BOJ_11586 {

    private static int N;
    private static String[] strArr;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        strArr = new String[N];
        for (int i = 0; i < N; i++) {
            strArr[i] = sc.next();
            sb.append(strArr[i]).append("\n");
        }

        int K = sc.nextInt();
        if (K == 2) reverseLeftRight();
        else if (K == 3) reverseUpDown();

        System.out.print(sb.toString());
    }

    private static void reverseLeftRight() {
        sb = new StringBuilder();
        for (int y = 0; y < N; y++) {
            for (int x = N - 1; x >= 0; x--) {
                sb.append(strArr[y].charAt(x));
            }
            sb.append("\n");
        }
    }

    private static void reverseUpDown() {
        sb = new StringBuilder();
        for (int y = N - 1; y >= 0; y--) {
            for (int x = 0; x < N; x++) {
                sb.append(strArr[y].charAt(x));
            }
            sb.append("\n");
        }
    }
}
