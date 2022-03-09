import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def dfs(x, y):
    if visited[y][x] == -1:
        visited[y][x] = 1
        for i in range(4):
            nx, ny = x + dx[i], y + dy[i]
            if 0 <= nx < N and 0 <= ny < N and matrix[y][x] < matrix[ny][nx]:
                visited[y][x] = max(visited[y][x], dfs(nx, ny) + 1)
    return visited[y][x]

N = int(input())
matrix = [list(map(int, input().split())) for _ in range(N)]
visited = [[-1] * N for _ in range(N)]
result = 0
for i in range(N):
    for j in range(N):
        if visited[i][j] == -1:
            result = max(result, dfs(j, i))
print(result)