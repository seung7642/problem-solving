from sys import stdin
from collections import deque


dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def bfs(board, x, y):
    q = deque()
    q.append((x, y))
    board[y][x] = 0
    cnt = 1

    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < N and 0 <= ny < N:
                if board[ny][nx] == 1:
                    board[ny][nx] = 0
                    q.append((nx, ny))
                    cnt += 1
    return cnt


N = int(input())
board = []
for _ in range(N):
    board.append(list(map(int, input())))

cnt = []
for i in range(N):
    for j in range(N):
        if board[i][j] == 1:
            cnt.append(bfs(board, j, i))

cnt.sort()
print(len(cnt))
for i in range(len(cnt)):
    print(cnt[i])
