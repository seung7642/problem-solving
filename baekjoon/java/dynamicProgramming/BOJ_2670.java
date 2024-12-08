package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_2670 {

    private static int N;
    private static double[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new double[N];
        arr = new double[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        double result = 0;
        cache[0] = arr[0];
        for (int i = 1; i < N; i++) {
            if (arr[i] < cache[i - 1] * arr[i]) {
                cache[i] = cache[i - 1] * arr[i];
            } else {
                cache[i] = arr[i];
            }
            result = Math.max(result, cache[i]);
        }

        System.out.printf("%.3f", result);
    }
}
