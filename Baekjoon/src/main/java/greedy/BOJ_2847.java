package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2847 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        for (int i = N - 1; i > 0; i--) {
            int gap = arr[i] - arr[i - 1];
            if (gap <= 0) {
                ans += (Math.abs(gap) + 1);
                arr[i - 1] -= (Math.abs(gap) + 1);
            }
        }

        System.out.println(ans);
    }
}
