class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        dp = [row for row in matrix]
        for i in range(1, len(dp)):
            for j in range(len(dp[i])):
                if j == 0:
                    dp[i][j] = dp[i][j] + min(dp[i - 1][j], dp[i - 1][j + 1])
                elif j == len(dp[i]) - 1:
                    dp[i][j] = dp[i][j] + min(dp[i - 1][j - 1], dp[i - 1][j])
                else:
                    dp[i][j] = dp[i][j] + min(dp[i - 1][j - 1], dp[i - 1][j], dp[i - 1][j + 1])

        return min(matrix[-1])
        