package divideAndConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1629 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            System.out.println(cal(a, b, c) % c);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static long cal(long a, long b, long c) {
        if (b == 0) return 1;
        if (b == 1) return a;

        long n = cal(a, b / 2, c) % c;
        if (b % 2 == 0) { // 짝수 지수
            return (n * n) % c;
        } else { // 홀수 지수
            return (((n * n) % c) * a) % c;
        }
    }
}
