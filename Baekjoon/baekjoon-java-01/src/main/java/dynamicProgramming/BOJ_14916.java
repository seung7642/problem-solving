package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_14916 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[] arr = new int[100_001];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        arr[1] = -1;
        arr[2] = 1;
        arr[3] = -1;
        arr[4] = 2;
        arr[5] = 1;
        for (int i = 6; i <= N; i++) {
            if (arr[i - 2] == -1 || arr[i - 5] == -1) {
                if (arr[i - 2] != -1) {
                    arr[i] = arr[i - 2] + 1;
                } else if (arr[i - 5] != -1) {
                    arr[i] = arr[i - 5] + 1;
                } else {
                    arr[i] = -1;
                }
            } else {
                arr[i] = Math.min(arr[i - 2], arr[i - 5]) + 1;
            }
        }

        System.out.println(arr[N]);
    }
}
