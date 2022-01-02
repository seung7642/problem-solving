package highScoreKit.heap;

import java.util.*;

public class B {

    public static void main(String[] args) {
        int[][] jobs;
        jobs = new int[][]{{0, 3}, {1, 9}, {2, 6}};
        System.out.println(solution(jobs)); // 답: 9
    }

    public static int solution(int[][] jobs) {
        int answer = 0;
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);
        Queue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        int end = 0; // 진행 중인 작업이 끝나는 시간
        int jobsIdx = 0; // jobs 배열의 인덱스
        int count = 0; // 수행된 요청 갯수

        // 요청이 모두 수행될 때까지 반복
        while (count < jobs.length) {

            // 하나의 작업이 완료되는 시점까지 들어온 모든 요청을 큐에 넣습니다.
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
                pq.add(jobs[jobsIdx++]);
            }

            // 큐가 비어있다면 작업 완료(end) 이후에 다시 요청이 들어온다는 의미입니다.
            if (pq.isEmpty()) {
                end = jobs[jobsIdx][0];

            // 작업이 끝나기 전(end 이전) 들어온 요청 중 가장 수행시간이 짧은 요청부터 수행합니다.
            } else {
                int[] tmp = pq.poll();
                answer += (end - tmp[0]) + tmp[1];
                end += tmp[1];
                count++;
            }
        }

        return answer / jobs.length;
    }

}
