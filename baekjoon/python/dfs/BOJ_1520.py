import sys
input = sys.stdin.readline

N, M = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(N)]
visited = [[-1] * M for _ in  range(N)]

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def dfs(x, y):
    if x == M - 1 and y == N - 1:
        return 1
    if visited[y][x] != -1:
        return visited[y][x]

    visited[y][x] = 0
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < M and 0 <= ny < N:
            if matrix[ny][nx] < matrix[y][x]:
                visited[y][x] += dfs(nx, ny)
    return visited[y][x]

print(dfs(0, 0))
