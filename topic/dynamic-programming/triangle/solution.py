class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:
        dp = [[0 for _ in range(len(row))] for row in triangle]
        dp[0] = triangle[0]

        for i in range(1, len(dp)):
            prev_row = dp[i - 1]
            dp[i][0] = prev_row[0] + triangle[i][0]
            for j in range(1, i):
                dp[i][j] = min(prev_row[j - 1], prev_row[j]) + triangle[i][j]
            
            dp[i][-1] = prev_row[-1] + triangle[i][-1]
        
        return min(dp[-1])
        