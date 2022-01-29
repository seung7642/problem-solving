package highScoreKit.dp;

public class D {

    public static void main(String[] args) {
        int[] money;

        money = new int[]{1, 2, 3, 1};
        System.out.println(solution(money)); // 답: 4
    }

    public static int solution(int[] money) {
        int answer = 0;
        if (money.length == 3) {
            for (int i = 0; i < 3; i++) {
                if (answer < money[i]) {
                    answer = money[i];
                }
            }
            return answer;
        }

        int[] dpStealFirst = new int[money.length];
        int[] dpIgnoreFirst = new int[money.length];

        // 첫 집을 무조건 털고 마지막 집은 무조건 털지 않는 경우
        dpStealFirst[0] = money[0];
        dpStealFirst[1] = Math.max(money[0], money[1]);

        // 첫 집을 무조건 털지 않는 경우
        dpIgnoreFirst[0] = 0;
        dpIgnoreFirst[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            dpIgnoreFirst[i] = Math.max(dpIgnoreFirst[i - 1], money[i] + dpIgnoreFirst[i - 2]);
            answer = Math.max(answer, dpIgnoreFirst[i]);

            if (i == money.length - 1) {
                break;
            }

            dpStealFirst[i] = Math.max(dpStealFirst[i - 1], money[i] + dpStealFirst[i - 2]);
            answer = Math.max(answer, dpStealFirst[i]);
        }
        return answer;
    }
}
