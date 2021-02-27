package search.binarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ_1300 {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int N = Integer.parseInt(br.readLine());
            int K = Integer.parseInt(br.readLine());

            long left = 1, right = K;
            long ans = 0;

            while (left <= right) {
                long mid = (left + right) / 2;
                long cnt = 0;

                for (int i = 1; i <= N; i++) {
                    cnt += Math.min(mid / i, N);
                }

                if (cnt < K) {
                    left = mid + 1;
                } else {
                    ans = mid;
                    right = mid - 1;
                }
            }

            System.out.println(ans);
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
}
