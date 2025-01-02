class Solution:
    def partition(self, s: str) -> List[List[str]]:
        def isPalindrome(s: str) -> bool:
            return s == s[::-1]

        def dfs(start_idx: int, paths: List[int]):
            if start_idx >= n:
                result.append(paths)
                return

            for end_idx in range(start_idx, n):
                substring = s[start_idx:end_idx + 1]
                if isPalindrome(substring):
                    dfs(end_idx + 1, paths + [substring])

        n = len(s)
        result = []
        dfs(0, [])
        return result
