package highScoreKit.stackAndQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class A {

    public static void main(String[] args) {

        int[] progresses;
        int[] speeds;
        progresses = new int[]{93, 30, 55};
        speeds = new int[]{1, 30, 5};
        System.out.println(solution(progresses, speeds)); // 답: [2, 1]

        progresses = new int[]{95, 90, 99, 99, 80, 99};
        speeds = new int[]{1, 1, 1, 1, 1, 1};
        System.out.println(solution(progresses, speeds)); // 답: [1, 3, 2]
    }

    public static List<Integer> solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();

        // 처음 풀었을 때
//        Queue<Integer> q = new LinkedList<>();
//        for (int i = 0; i < progresses.length; i++) {
//            q.add(i);
//        }
//        int day = 0;
//        while (!q.isEmpty()) {
//            int idx = q.poll();
//            int rest = 100 - (progresses[idx] + (speeds[idx] * day));
//            if (rest <= speeds[idx]) {
//                day += 1;
//            } else if (rest % speeds[idx] == 0) {
//                day += rest / speeds[idx];
//            } else {
//                day += (rest / speeds[idx]) + 1;
//            }
//
//            int cnt = 1;
//            while (!q.isEmpty()) {
//                idx = q.peek();
//                if (progresses[idx] + (speeds[idx] * day) >= 100) {
//                    q.poll();
//                    cnt++;
//                } else {
//                    break;
//                }
//            }
//            answer.add(cnt);
//        }

        // 두 번째 풀이 - 리팩토링
        int day = 0, num = -1, len = progresses.length;
        int[] deployDay = new int[len];
        for (int i = 0; i < len; i++) {
            boolean exec = false;
            while (progresses[i] + (speeds[i] * day) < 100) {
                day++;
                exec = true;
            }
            if (exec) {
                num++;
            }
            deployDay[num]++;
        }

        for (int i : deployDay) {
            if (i == 0) {
                break;
            }
            answer.add(i);
        }

        return answer;
    }
}
