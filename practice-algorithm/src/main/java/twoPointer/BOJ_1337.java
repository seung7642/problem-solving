package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1337 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        System.out.println(twoPointers());

        br.close();
    }

    private static int twoPointers() {
        int cnt = 1, max = 1, idx = 0;

        for (int i = 1; i < N; i++) {
            cnt++;

            while (arr[i] - arr[idx] > 4) {
                idx++;
                cnt--;
            }
            max = Math.max(max, cnt);
        }

        if (max > 5) max = 5;
        return 5 - max;
    }
}
