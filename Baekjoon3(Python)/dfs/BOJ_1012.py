import sys
sys.setrecursionlimit(10_000)

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def dfs(x, y):
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < M and 0 <= ny < N and board[ny][nx] == 1:
            board[ny][nx] = 2
            dfs(nx, ny)


TC = int(input())
for _ in range(TC):
    M, N, K = map(int, input().split())
    board = [[0 for _ in range(M)] for _ in range(N)]
    for _ in range(K):
        x, y = map(int, input().split())
        board[y][x] = 1

    cnt = 0
    for i in range(N):
        for j in range(M):
            if board[i][j] == 1:
                cnt += 1
                board[i][j] = 2
                dfs(j, i)

    print(cnt)
