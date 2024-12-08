package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1735 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A1 = Integer.parseInt(st.nextToken());
            int B1 = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int A2 = Integer.parseInt(st.nextToken());
            int B2 = Integer.parseInt(st.nextToken());

            int numerator = A1 * B2 + A2 * B1;
            int denominator = B1 * B2;
            int gcdVal = gcd(numerator, denominator);

            numerator /= gcdVal;
            denominator /= gcdVal;

            System.out.println(numerator + " " + denominator);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
