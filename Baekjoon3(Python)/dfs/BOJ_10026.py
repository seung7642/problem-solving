import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

N = int(input().rstrip())
matrix = [list(input().rstrip()) for _ in range(N)]
visited = [[False] * N for _ in range(N)]

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def dfs(x, y):
    visited[y][x] = True
    current_color = matrix[y][x]

    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < N and 0 <= ny < N:
            if visited[ny][nx] == False:
                if matrix[ny][nx] == current_color:
                    dfs(nx, ny)

three_cnt, two_cnt = 0, 0
for i in range(N):
    for j in range(N):
        if visited[i][j] == False:
            dfs(j, i)
            three_cnt += 1

for i in range(N):
    for j in range(N):
        if matrix[i][j] == 'R':
            matrix[i][j] = 'G'

visited = [[False] * N for _ in range(N)]

for i in range(N):
    for j in range(N):
        if visited[i][j] == False:
            dfs(j, i)
            two_cnt += 1

print(three_cnt, two_cnt)
