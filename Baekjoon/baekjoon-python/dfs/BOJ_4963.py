import sys
sys.setrecursionlimit(10000)

dx = [0, 0, -1, 1, -1, -1, 1, 1]
dy = [-1, 1, 0, 0, -1, 1, -1, 1]


def dfs(x, y):
    visited[y][x] = True
    for i in range(8):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < w and 0 <= ny < h and not visited[ny][nx] and board[ny][nx] == 1:
            dfs(x + dx[i], y + dy[i])


while True:
    w, h = map(int, input().split())
    if w == 0 and h == 0:
        exit()

    board = [list(map(int, input().split())) for _ in range(h)]
    visited = [[False for _ in range(w)] for _ in range(h)]

    cnt = 0
    for i in range(h):
        for j in range(w):
            if not visited[i][j] and board[i][j] == 1:
                cnt += 1
                dfs(j, i)
    print(cnt)
