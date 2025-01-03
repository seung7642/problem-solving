class Solution:  
    def minCut(self, s: str) -> int:  
        n = len(s)
        palindrome_memoization = [[False] * n for _ in range(n)]
        for i in range(n):
            palindrome_memoization[i][i] = True

        for length in range(2, n + 1):
            for start in range(n - length + 1):
                end = start + length - 1
                if length == 2:
                    palindrome_memoization[start][end] = (s[start] == s[end])
                else:
                    palindrome_memoization[start][end] = palindrome_memoization[start + 1][end - 1] and (s[start] == s[end])
                
        min_cuts_from_index = [-1] * n  
        def findMinimumCut(start: int) -> int:
            if start >= n or palindrome_memoization[start][n - 1]:
                return 0
            
            if min_cuts_from_index[start] != -1:
                return min_cuts_from_index[start]
            
            min_cuts = n
            for end in range(start, n - 1):
                if palindrome_memoization[start][end]:
                    min_cuts = min(min_cuts, 1 + findMinimumCut(end + 1))

            min_cuts_from_index[start] = min_cuts
            return min_cuts
        
        return findMinimumCut(0)
