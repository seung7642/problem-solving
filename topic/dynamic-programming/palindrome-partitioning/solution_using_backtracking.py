class Solution:
    def partition(self, s: str) -> List[List[str]]:
        def isPalindrome(s: str) -> bool:
            return s == s[::-1]

        def dfs(s: str, paths: List[str]):
            if not s:
                result.append(paths[:])
                return
            
            for i in range(1, len(s) + 1):
                if isPalindrome(s[:i]):
                    dfs(s[i:], paths + [s[:i]])
        
        result = []
        dfs(s, [])
        return result
