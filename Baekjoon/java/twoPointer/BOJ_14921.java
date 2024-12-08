package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14921 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(twoPointers());

        br.close();
    }

    private static int twoPointers() {
        int ans = 1_000_000_000, sum;
        int left = 0, right = N - 1;

        while (left < right) {
            sum = arr[left] + arr[right];

            if (Math.abs(ans) > Math.abs(sum)) {
                ans = sum;
            }

            if (sum < 0) left++;
            else right--;
        }

        return ans;
    }
}
