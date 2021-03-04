package math;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2485 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
            }

            // 각 가로수간의 거리를 구한다.
            int[] distance = new int[N - 1];
            for (int i = 0; i < N - 1; i++) {
                distance[i] = arr[i + 1] - arr[i];
            }

            // 거리 간의 최대공약수를 구한다.
            int gcd = gcd(distance[0], distance[1]);
            for (int i = 2; i < N - 2; i++) {
                gcd = gcd(gcd, distance[i]);
                if (gcd == 1) break;
            }

            int result = 0;
            for (int i = 0; i < N - 1; i++) {
                result += (distance[i] / gcd) - 1;
            }

            System.out.println(result);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
