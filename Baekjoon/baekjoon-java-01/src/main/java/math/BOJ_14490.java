package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_14490 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            StringBuilder sb = new StringBuilder();
            int[] arr = Arrays.stream(br.readLine().split(":"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int greatestCommonDivisor = gcd(Math.max(arr[0], arr[1]), Math.min(arr[0], arr[1]));
            sb.append(arr[0] / greatestCommonDivisor).append(":").append(arr[1] / greatestCommonDivisor);
            System.out.println(sb.toString());
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
