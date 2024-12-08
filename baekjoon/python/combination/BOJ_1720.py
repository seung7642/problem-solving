import sys
input = lambda: sys.stdin.readline().rstrip()

dp = [0] * 31
dp[1] = 1
dp[2] = 3
for i in range(3, 30 + 1):
    dp[i] = dp[i - 1] + dp[i - 2] * 2

dp2 = [[0] * 31 for _ in range(31)]

N = int(input())

if N % 2 == 0: 
    dp2[N//2][N//2 + 1] = 3
    dp2[N//2 - 1][N//2 + 2] = 5

    left = N//2 - 2
    right = N//2 + 3
    while 0 <= left and right <= N:
        dp2[left][right] = dp2[left + 1][right - 1] + dp2[left + 2][right - 2] * 2
        left -= 1
        right += 1
else:
    dp2[N//2 + 1][N//2 + 1] = 1
    dp2[N//2][N//2 + 2] = 1

    left = N//2 - 1
    right = N//2 + 3
    while 0 <= left and right <= N:
        dp2[left][right] = dp2[left + 1][right - 1] + dp2[left + 2][right - 2] * 2
        left -= 1
        right += 1

result = (dp[N] + dp2[1][N]) // 2
print(result)
