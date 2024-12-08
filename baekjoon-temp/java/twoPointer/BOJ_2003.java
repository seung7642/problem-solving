package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2003 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N, M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int ans = twoPointer();
        System.out.println(ans);

        br.close();
    }

    private static int twoPointer() {
        int count = 0; // M이 되는 경우의 수
        int sum = 0, end = 0;

        for (int start = 0; start < N; start++) {
            sum += arr[start];

            if (sum == M) {
                count++;
                sum -= arr[end++]; // sum에서 end 위치의 값을 빼고, end 증가
            } else if (sum > M) {
                while (sum > M) {
                    sum -= arr[end++];
                    if (sum == M) count++;
                }
            }
        }
        return count;
    }
}
