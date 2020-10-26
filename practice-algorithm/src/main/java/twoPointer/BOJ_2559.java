package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2559 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, K;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        System.out.println( twoPointers() );

        br.close();
    }

    private static int twoPointers() {
        int sum = 0, max = -1_000_000_000;
        int left = 0, right = K;

        for (int i = 0; i < K; i++) {
            sum += arr[i];
        }

        while (true) {
            max = Math.max(max, sum);
            if (right == N) break;
            sum -= arr[left++];
            sum += arr[right++];
        }

        return max;
    }
}
