package highScoreKit.greedy;

import java.util.Arrays;

public class F {

    public static void main(String[] args) {
        int[][] routes;

        routes = new int[][]{{-20,-15}, {-14,-5}, {-18,-13}, {-5,-3}};
        System.out.println(solution(routes)); // 답: 2

        routes = new int[][]{{1, 6,}, {2, 3}, {4, 5}, {6, 7}};
        System.out.println(solution(routes)); // 답: 3
    }

    public static int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int prevEndTime = -30001;
        for (int[] route : routes) {
            if (prevEndTime < route[0]) {
                prevEndTime = route[1];
                answer++;
            }
        }
        return answer;
    }

}
