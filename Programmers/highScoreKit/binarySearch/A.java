package highScoreKit.binarySearch;

import java.util.Arrays;

public class A {

    public static void main(String[] args) {
        int n;
        int[] times;

        n = 6;
        times = new int[]{7, 10};
        System.out.println(solution(n, times)); // 답: 28
    }

    public static long solution(int n, int[] times) {
        long answer = 0;

        Arrays.sort(times);
        long left = 0;
        long right = (long) n * times[times.length - 1]; // 최악의 경우일 때 걸리는 시간
        while (left <= right) {
            long mid = (left + right) / 2;
            long peopleCnt = 0;

            // mid만큼의 시간이 주어졌을 때, 각 심사관들이 처리할 수 있는 인원 수
            for (int i = 0; i < times.length; i++) {
                peopleCnt += mid / times[i];
            }

            if (peopleCnt < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }

        return answer;
    }
}
