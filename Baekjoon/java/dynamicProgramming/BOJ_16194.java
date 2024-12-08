package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_16194 {

    private static final int INF = 1_000_000;
    private static int N;
    private static int[] arr, cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cache = new int[N + 1];
        arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            cache[i] = INF;
        }


        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++) {
                cache[j] = Math.min(cache[j], cache[j - i] + arr[i]);
            }
        }

        System.out.println(cache[N]);
    }
}
