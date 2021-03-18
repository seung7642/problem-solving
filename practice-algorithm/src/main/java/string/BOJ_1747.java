package string;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1747 {

    private static final int MAX = 1_004_001;
    private static boolean[] prime = new boolean[MAX];

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            setPrime();

            int result = 0;
            for (int i = N; i <= MAX; i++) {
                if (i < 10 && prime[i]) { // 1 자릿수는 무조건 팰린드롬이다.
                    result = i;
                    break;
                } else {
                    if (prime[i] && isPelindrome(String.valueOf(i))) {
                        result = i;
                        break;
                    }
                }
            }

            System.out.println(result);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static boolean isPelindrome(String str) {
        for (int i = 0, j = str.length() - 1; i < str.length(); i++, j--) {
            if (i >= j) return true;
            if (str.charAt(i) != str.charAt(j)) break;
        }
        return false;
    }

    private static void setPrime() {
        for (int i = 2; i < MAX; i++) {
            prime[i] = true;
        }

        int num = (int) Math.sqrt(MAX);
        for (int i = 2; i <= num; i++) {
            if (prime[i]) {
                for (int j = i * 2; j < MAX; j += i) {
                    prime[j] = false;
                }
            }
        }
    }
}
