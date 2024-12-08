package highScoreKit.heap;

import java.util.PriorityQueue;
import java.util.Queue;

public class A {

    public static void main(String[] args) {
        int[] scoville;
        int K;
        scoville = new int[]{1, 2, 3, 9, 10, 12};
        K = 7;
        System.out.println(solution(scoville, K)); // 답: 2
    }

    public static int solution(int[] scoville, int K) {
        int answer = 0;

        Queue<Integer> pq = new PriorityQueue<>();
        for (int i : scoville) {
            pq.add(i);
        }

        while (pq.peek() < K) {
            if (pq.size() < 2) {
                return -1;
            }
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + (b * 2));
            answer++;
        }

        return answer;
    }
}
