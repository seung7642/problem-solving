package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2467 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, pick1, pick2;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        twoPointers();
        System.out.println(pick1 + " " + pick2);
        br.close();
    }

    private static int twoPointers() {
        int ans = 2_000_000_000;
        int left = 0, right = N - 1;

        while (left < right) {
            if (Math.abs(arr[left] + arr[right]) < ans) {
                ans = Math.abs(arr[left] + arr[right]);
                pick1 = arr[left];
                pick2 = arr[right];
            }

            if (arr[left] + arr[right] < 0) left++;
            else right--;
        }

        return ans;
    }
}
