package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_1744 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, ans;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int left = 0, right = N - 1;
        while (left < right) {
            if (arr[left] < 1 && arr[left + 1] < 1) {
                ans += arr[left] * arr[left + 1];
            } else {
                break;
            }
            left += 2;
        }

        while (right > 0) {
            if (arr[right] > 1 && arr[right - 1] > 1) {
                ans += arr[right] * arr[right - 1];
            } else {
                break;
            }
            right -= 2;
        }

        while (right >= left) {
            ans += arr[right--];
        }

        System.out.println(ans);
    }
}
