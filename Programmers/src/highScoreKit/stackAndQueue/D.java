package highScoreKit.stackAndQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class D {

    public static void main(String[] args) {
        int[] prices = new int[]{1, 2, 3, 2, 3};
        System.out.println(Arrays.toString(solution(prices))); // 답: [4, 3, 1, 1, 0]


    }

    public static int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                answer[i]++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }

        return answer;
    }
}
