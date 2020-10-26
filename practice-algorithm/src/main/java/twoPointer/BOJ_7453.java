package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static int[][] arr;
    private static long[] ab, cd;

    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[4][N];
        ab = new long[N * N];
        cd = new long[N * N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[0][i] = Integer.parseInt(st.nextToken());
            arr[1][i] = Integer.parseInt(st.nextToken());
            arr[2][i] = Integer.parseInt(st.nextToken());
            arr[3][i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ab[i * N + j] = arr[0][i] + arr[1][j];
                cd[i * N + j] = arr[2][i] + arr[3][j];
            }
        }

        Arrays.sort(cd); // cd 배열을 가지고 이진 탐색을 해야하기 때문에 정렬한다.

        long ans = twoPointer();
        System.out.println(ans);

        br.close();
    }

    private static long twoPointer() {
        long count = 0;

        for (int i = 0; i < N * N; i++) {
            long left = 0, right = N * N; // 이진 탐색을 위한 변수

            while (left < right) {
                long mid = (left + right) / 2;
                if (ab[i] + cd[ (int) mid ] < 0) left = mid + 1;
                else right = mid;
            }
            long lowerBound = right;
            left = 0;
            right = N * N;

            while (left < right) {
                long mid = (left + right) / 2;
                if (ab[i] + cd[ (int) mid ] <= 0) left = mid + 1;
                else right = mid;
            }
            long upperBound = right;

            count += upperBound - lowerBound;
        }

        return count;
    }
}
