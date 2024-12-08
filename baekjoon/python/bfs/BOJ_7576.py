from collections import deque

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]
day = 0
rawTomatoCnt = 0


def bfs():
    global day, rawTomatoCnt
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx, ny = dx[i] + x, dy[i] + y
            if 0 <= nx < M and 0 <= ny < N and board[ny][nx] == 0:
                board[ny][nx] = board[y][x] + 1
                day = board[ny][nx] - 1
                rawTomatoCnt -= 1
                q.append((nx, ny))


M, N = map(int, input().split())
board = [list(map(int, input().split())) for _ in range(N)]
q = deque()
for i in range(N):
    for j in range(M):
        if board[i][j] == 1:
            q.append((j, i))
        elif board[i][j] == 0:
            rawTomatoCnt += 1

if rawTomatoCnt == 0:
    print(0)
    exit()

bfs()
print(-1 if rawTomatoCnt != 0 else day)
