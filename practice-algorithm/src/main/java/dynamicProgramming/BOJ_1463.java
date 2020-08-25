package dynamicProgramming;

import java.util.Scanner;

// 문제: 1로 만들기
public class BOJ_1463 {

    public static int[] d;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        d = new int[n + 1];

        System.out.println(bottomUp(n));
        sc.close();
    }

    private static int bottomUp(int n) {
        d[0] = d[1] = 0;
        for (int i = 2; i <= n; i++) {
            d[i] = d[i - 1] + 1;
            if (i % 2 == 0)
                d[i] = Math.min(d[i], d[i / 2] + 1);
            if (i % 3 == 0)
                d[i] = Math.min(d[i], d[i / 3] + 1);
        }
        return d[n];
    }

    private static int topDown(int n) {
        if (n == 1) return 0;
        if (d[n] > 0) return d[n];

        d[n] = topDown(n - 1) + 1;
        if (n % 2 == 0) {
            int tmp = topDown(n / 2) + 1;
            if (d[n] > tmp)
                d[n] = tmp;
        }
        if (n % 3 == 0) {
            int tmp = topDown(n / 3) + 1;
            if (d[n] > tmp)
                d[n] = tmp;
        }
        return d[n];
    }
}
