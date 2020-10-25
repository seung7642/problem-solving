package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1806 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, S;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = twoPointer();
        System.out.println((ans == 1_000_000 ? 0 : ans));

        br.close();
    }

    private static int twoPointer() {
        int ans = 1_000_000, sum = 0;
        int start = 0, end = 0;

        while (true) {
            if (sum >= S) { // 연속합이 S 이상이면 sum에서 arr[start]를 뺀후 start를 우측으로 옮긴다.
                sum -= arr[start++];
                ans = Math.min(ans, (end - start));
            } else if (end == N) {
                break;
            } else {
                sum += arr[end++];
            }
        }
        return ans;
    }
}
