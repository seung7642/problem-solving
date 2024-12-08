package highScoreKit.bruteforce;

import java.util.Arrays;

public class C {

    public static void main(String[] args) {
        int brown;
        int yellow;

        brown = 10;
        yellow = 2;
        System.out.println(Arrays.toString(solution(brown, yellow))); // 답: [4, 3]

        brown = 8;
        yellow = 1;
        System.out.println(Arrays.toString(solution(brown, yellow))); // 답: [3, 3]

        brown = 24;
        yellow = 24;
        System.out.println(Arrays.toString(solution(brown, yellow))); // 답: [8, 6]
    }

    public static int[] solution(int brown, int yellow) {
        int cnt = 1;

        for (int i = 1; i <= yellow / 2; i++) {
            if (yellow % i == 0) {
                int yellowRow = yellow / i;
                int yellowCol = i;
                int num = (yellowCol * 2) + (yellowRow * 2) + 4;
                if (num == brown) {
                    return new int[]{yellowRow + 2, yellowCol + 2};
                }
            }
        }

        return new int[]{0, 0};
    }
}
