class Solution:
    def minCut(self, s: str) -> int:
        # cutsDP[i]: s[0:i+1] 문자열의 최소 분할 횟수
        cutsDP = [0] * len(s)

        palindromeDP = [[False] * len(s) for _ in range(len(s))]
        self.buildPalindromeDP(s, len(s), palindromeDP)

        for end in range(len(s)):
            minimumCut = end # 최악의 경우: 모든 문자를 각각 분할

            # s[start:end+1] 구간이 팰린드롬인 모든 경우 확인
            for start in range(end + 1):
                if palindromeDP[start][end]:
                    if start == 0:
                        minimumCut = 0 # 전체가 팰린드롬
                    else:
                        # start-1 까지의 최소 분할 횟수 + 1 
                        minimumCut = min(minimumCut, cutsDP[start - 1] + 1)

            cutsDP[end] = minimumCut

        return cutsDP[len(s) - 1]

    def buildPalindromeDP(self, s, n, palindromeDP):
        for end in range(len(s)):
            for start in range(end + 1):
                if end - start <= 2:
                    palindromeDP[start][end] = s[start] == s[end]
                else:
                    palindromeDP[start][end] = s[start] == s[end] and palindromeDP[start + 1][end - 1]