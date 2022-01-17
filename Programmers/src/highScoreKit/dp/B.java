package highScoreKit.dp;

public class B {

    public static void main(String[] args) {
        int[][] triangle;

        triangle = new int[][]{{7}, {3, 8}, {8, 1, 0}, {2, 7, 4, 4}, {4, 5, 2, 6, 5}};
        System.out.println(solution(triangle)); // 답: 30
    }

    public static int solution(int[][] triangle) {
        int answer = 0;

        int[][] dp = new int[501][501];
        dp[0][0] = triangle[0][0];
        dp[1][0] = dp[0][0] + triangle[1][0];
        dp[1][1] = dp[0][0] + triangle[1][1];

        for (int i = 2; i < triangle.length; i++) {
            for (int j = 0; j < triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + triangle[i][j];
                } else if (j == triangle[i].length - 1) {
                    dp[i][j] = dp[i - 1][j - 1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + triangle[i][j];
                }
            }
        }

        for (int i = 0; i < dp[triangle.length - 1].length; i++) {
            answer = Math.max(answer, dp[triangle.length - 1][i]);
        }

        return answer;
    }
}
