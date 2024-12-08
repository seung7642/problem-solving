import sys
sys.setrecursionlimit(10 ** 6)
input = lambda: sys.stdin.readline().rstrip()

# mCn = m! / (m-n)! * n!
dp = [0] * 30
dp[1] = 1
dp[2] = 2
for i in range(3, 30):
    dp[i] = i * dp[i - 1]

T = int(input())
for _ in range(T):
    N, M = map(int, input().split())
    ans = 0
    if N == M or N == 0:
        print(1)
    else:
        print(dp[M] // (dp[M - N] * dp[N]))
