class Solution:
    def partition(self, s: str) -> List[List[str]]:
        n = len(s)
        dp = [[False] * n for _ in range(n)]

        # All substrings with length of 1 are palindromes.
        for i in range(n):
            dp[i][i] = True

        # Check if substrings of length 2 or greater are palindromes.
        for length in range(2, n + 1):
            for start in range(n - length + 1):
                end = start + length - 1
                if length == 2:
                    dp[start][end] = (s[start] == s[end])
                else:
                    dp[start][end] = (s[start] == s[end] and dp[start + 1][end - 1])

        def dfs(start: int, paths: List[str]):
            if start >= n:
                result.append(paths[:])
                return
            
            for end in range(start, n):
                if dp[start][end]:
                    dfs(end + 1, paths + [s[start:end + 1]])
        
        result = []
        dfs(0, [])
        return result
