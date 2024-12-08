package primeNumber;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_6588 {

    private static final int MAX = 1_000_000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[] prime;

    public static void main(String[] args) throws IOException {
        prime = new boolean[MAX + 1];

        for (int i = 2; i <= MAX; i++) {
            prime[i] = true;
        }

        for (int i = 2; i <= MAX; i++) {
            for (int j = i * 2; j <= MAX; j += i) {
                if(!prime[j]) continue;
                prime[j] = false;
            }
        }

        while (true) {
            boolean isPrime = false;
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            for (int i = 2; i <= N / 2; i++) {
                if (prime[i] && prime[N - i]) {
                    System.out.println(N + " = " + i + " + " + (N - i));
                    isPrime = true;
                    break;
                }
            }

            if (!isPrime)
                System.out.println("Goldbach's conjecture is wrong.");
        }

        br.close();
    }
}
