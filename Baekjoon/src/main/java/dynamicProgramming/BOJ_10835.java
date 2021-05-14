package dynamicProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_10835 {

    private static int N;
    private static int[] leftCardDummy, rightCardDummy;
    private static int[][] cache;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        leftCardDummy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        rightCardDummy = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        cache = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(cache[i], -1);
        }

        System.out.println(solution(0, 0));
    }

    private static int solution(int left, int right) {
        if (left == N || right == N) return 0;

        if (cache[left][right] != -1) return cache[left][right];

        cache[left][right] = Math.max(solution(left + 1, right + 1), solution(left + 1, right));

        if (leftCardDummy[left] > rightCardDummy[right]) {
            cache[left][right] = Math.max(cache[left][right], solution(left, right + 1) + rightCardDummy[right]);
        }

        return cache[left][right];
    }
}
