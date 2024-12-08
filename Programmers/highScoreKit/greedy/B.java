package highScoreKit.greedy;

import java.util.ArrayList;
import java.util.List;

public class B {

    public static void main(String[] args) {
        String name;

        name = "BBAAAA";
        System.out.println(solution(name)); // 답:

        name = "JEROEN";
        System.out.println(solution(name)); // 답: 56

        name = "JAN";
        System.out.println(solution(name)); // 답: 23
    }

    public static int solution(String name) {
        int answer = 0;
        int len = name.length();

        int min = len - 1;

        for (int i = 0; i < len; i++) {
            char ch = name.charAt(i);
            int move = Math.min(ch - 'A', 'Z' - ch + 1);
            answer += move;

            int nextIdx = i + 1;
            while (nextIdx < len && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }
            min = Math.min(min, (i * 2) + len - nextIdx);
        }

        answer += min;

        return answer;
    }
}
