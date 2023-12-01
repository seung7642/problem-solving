package graph.backtracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_15658 {

    private static int N;
    private static int[] arr, operatorNum;
    private static int resultMax = Integer.MIN_VALUE;
    private static int resultMin = Integer.MAX_VALUE;

    public static void main(String[] args) throws  Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        operatorNum = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        backtrack(1, arr[0]);
        System.out.println(resultMax + "\n" + resultMin);
    }

    private static void backtrack(int idx, int sum) {
        if (idx >= N) {
            resultMax = Math.max(resultMax, sum);
            resultMin = Math.min(resultMin, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operatorNum[i] == 0) continue;
            operatorNum[i]--;
            if (i == 0) { // + 연산
                backtrack(idx + 1, sum + arr[idx]);
            } else if (i == 1) { // - 연산
                backtrack(idx + 1, sum - arr[idx]);
            } else if (i == 2) { // * 연산
                backtrack(idx + 1, sum * arr[idx]);
            } else if (i == 3) { // / 연산
                backtrack(idx + 1, sum / arr[idx]);
            }
            operatorNum[i]++;
        }
    }
}
