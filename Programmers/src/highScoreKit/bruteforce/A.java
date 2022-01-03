package highScoreKit.bruteforce;

import java.util.Arrays;

public class A {

    public static void main(String[] args) {
        int[] answers;

        answers = new int[]{1,2,3,4,5};
        System.out.println(Arrays.toString(solution(answers))); // 답: [1]

        answers = new int[]{1,3,2,4,2};
        System.out.println(Arrays.toString(solution(answers))); // 답: [1,2,3]
    }

    public static int[] solution(int[] answers) {
        int[][] person = new int[][] {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] answerCnt = new int[3];
        int max = 0, maxCnt = 0;

        for (int i = 0; i < answers.length; i++) {
            int answer1 = answers[i];
            for (int j = 0; j < 3; j++) {
                int len = person[j].length;
                if (answer1 == person[j][i % len]) {
                    answerCnt[j]++;
                    if (max < answerCnt[j]) {
                        max = answerCnt[j];
                        maxCnt = 1;
                    } else if (max == answerCnt[j]) {
                        maxCnt++;
                    }
                }
            }
        }

        int[] answer = new int[maxCnt];
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            if (answerCnt[i] == max) {
                answer[idx++] = i + 1;
            }
        }

        return answer;
    }
}
