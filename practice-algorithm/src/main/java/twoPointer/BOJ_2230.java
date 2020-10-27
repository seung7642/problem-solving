package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2230 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M, ans = 2_000_000_000;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        twoPointers();
        System.out.println(ans);
        br.close();
    }

    private static void twoPointers() {
        int left = 0, right = 0;

        while (left < N) {
            int sum = arr[right] - arr[left];

            if (sum >= M) {
                ans = Math.min(ans, sum);
            }

            if (sum > M) left++;
            else if (right == N - 1) break; // right가 마지막 인덱스라면 종료.
            else if (sum == M) { // 차이가 M이라면 이 자체가 조건을 만족하는 최소 값이 된다.
                ans = M;
                break;
            }
            else if (sum < M) right++;
        }
    }
}
