package math;

import java.util.Scanner;

public class BOJ_9471 {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        int cnt, a, b;
        while (T-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            // m에 값에 따른 총 4가지 경우가 있다. (k(m)은 피보나치 수열의 피사노 주기의 길이)
            //   1. m이 2^n 형태인 경우 k(m) = 3 * 2^(n-1)
            //   2. m이 5^n 형태인 경우 k(m) = 4 * 5^n
            //   3. m이 2*5^n 형태인 경우 k(m) = 6n
            //   4. m이 10^n 형태인 경우 k(m) = 15 * 10^(n-1)  (단, n > 2 일때)

            cnt = 0;
            a = b = 1;
            while (true) {
                int tmp = (a + b) % m;
                a = b;
                b = tmp;
                cnt++;

                if (a == 1 && b == 1) break;
            }
            sb.append(n + " " + cnt).append("\n");
        }

        System.out.println(sb.toString());
    }
}
