from collections import deque

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]


def bfs(board, x, y):
    board[y][x] = 0
    q = deque()
    q.append((x, y))
    while q:
        x, y = q.popleft()
        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 <= nx < M and 0 <= ny < N and board[ny][nx] == 1:
                board[ny][nx] = 0
                q.append((nx, ny))


TC = range(int(input()))
for _ in TC:
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
                bfs(board, j, i)

    print(cnt)
