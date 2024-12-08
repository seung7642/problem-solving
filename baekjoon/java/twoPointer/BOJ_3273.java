package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3273 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, X, count;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());

        X = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        twoPointers();
        System.out.println(count);

        br.close();
    }

    private static void twoPointers() {
        int sum = 0;
        int left = 0, right = N - 1;

        while (left < right) {
            sum = arr[left] + arr[right];

            if (sum <= X) {
                if (sum == X) count++;
                left++;
            } else if (sum > X) {
                right--;
            }
        }
    }
}
