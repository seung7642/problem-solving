package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2436 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            long gcd = Long.parseLong(st.nextToken());
            long lcm = Long.parseLong(st.nextToken());
            long ans1 = gcd, ans2 = lcm;

            long xy = gcd * lcm; // gcd(x, y) * lcm(x, y) == x * y 가 성립한다. (수학공식)

            // xy의 약수이면서, 최대공약수의 배수인 것을 찾는다.
            for (long i = 2 * gcd; i * i <= xy; i += gcd) {
                if (xy % i == 0) {
                    long tmp = xy / i;

                    if (gcd(i, tmp) == gcd) {
                        if (ans1 + ans2 > i + tmp) {
                            ans1 = i;
                            ans2 = tmp;
                        }
                    }
                }
            }

            System.out.println(ans1 + " " + ans2);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
