# G(n) : n개의 계단이 있을 때 오를 수 있는 총 경우의 수
# G(n) : G(n - 2) + G(n - 1)
# G(1) : 1
# G(2) : 2
class Solution:
    def climbStairs(self, n: int) -> int:
        if n == 1:
            return 1

        G = [0] * (n + 1)
        G[1] = 1
        G[2] = 2
        for i in range(3, n + 1):
            G[i] = G[i - 2] + G[i - 1]

        return G[n]
        