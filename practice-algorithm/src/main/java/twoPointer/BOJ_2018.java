package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2018 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, count;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = i;
        }

        twoPointers();
        System.out.println(count);

        br.close();
    }

    private static void twoPointers() {
        int sum = 0;
        int left = 1, right = 1;

        while (true) {
            if (sum >= N) {
                if (sum == N) count++;
                sum -= arr[left++];
            } else if (right == N + 1) { // right가 마지막 인덱스라면 종료.
                break;
            } else if (sum < N) {
                sum += arr[right++];
            }
        }
    }
}
