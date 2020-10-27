package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2473 {

    private static long max = 3_000_000_000L;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static long[] arr;
    private static long[] pick = new long[3];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Long.parseLong(st.nextToken());

        Arrays.sort(arr);

        for (int i = 0; i < N - 2; i++)
            twoPointers(i);

        Arrays.sort(pick);

        for (int i = 0; i < 3; i++)
            System.out.print(pick[i] + " ");

        br.close();
    }

    private static void twoPointers(int index) {
        int left = index + 1, right = N - 1;

        while (left < right) {
            long sum = arr[left] + arr[right] + arr[index];
            long absSum = Math.abs(sum);

            if (absSum < max) {
                max = absSum;
                pick[0] = arr[left];
                pick[1] = arr[right];
                pick[2] = arr[index];
            }

            if (sum > 0) right--;
            else left++;
        }
    }
}
