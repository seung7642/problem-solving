package primeNumber;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2153 {

    private static final int MAX = 52 * 20 + 1;
    private static boolean[] prime = new boolean[MAX];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isLowerCase(c)) { // 소문자
                sum += (c - 'a' + 1);
            } else { // 대문자
                sum += (c - 'A' + 27);
            }
        }

        prime();

        if (prime[sum]) System.out.println("It is a prime word.");
        else System.out.println("It is not a prime word.");
    }

    private static void prime() {
        // 해당 문제에서는 1도 소수라고 가정했다.
        for (int i = 1; i < MAX; i++) {
            prime[i] = true;
        }

        for (int i = 2; i < MAX; i++) {
            for (int j = i + i; j < MAX; j += i) {
                if (!prime[i]) continue;
                prime[j] = false;
            }
        }
    }
}
