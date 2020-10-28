package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7795 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int T, N, M;
    private static int[] a, b;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            a = new int[N];
            b = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                a[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                b[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(a);
            Arrays.sort(b);

            System.out.println(twoPointers());
        }

        br.close();
    }

    private static int twoPointers() {
        int ans = 0, count = 0;
        int left = 0;  // a 배열의 위치를 가리킬 포인터
        int right = 0; // b 배열의 위치를 가리킬 포인터

        while (true) {
            if (a[left] <= b[right]) { // a가 b를 잡아먹을 수 없는 경우
                ans += count;
                if (left == N - 1) break; // 마지막 인덱스라면 종료
                left++;
            } else if (a[left] > b[right]) {
                count++;

                if (right == M - 1) { // 마지막 인덱스라면, 남은 a 배열의 물고기가 모든 b를 잡아먹을 수 있다.
                    ans += (N - left) * count;
                    break;
                }
                right++;
            }
        }

        return ans;
    }
}
