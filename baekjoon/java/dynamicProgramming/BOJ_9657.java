package dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_9657 {

    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[] arr = new boolean[1001];

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(in.readLine());
        arr[1] = true; // 상근이가 이기면 true
        arr[3] = true;
        arr[4] = true;
        arr[5] = true;
        for (int i = 5; i <= N; i++) {
            if (!arr[i - 1] || !arr[i - 3] || !arr[i - 4]) {
                arr[i] = true;
            } else {
                arr[i] = false;
            }
        }

        System.out.println(arr[N] ? "SK" : "CY");
    }
}
