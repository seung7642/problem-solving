import sys
sys.setrecursionlimit(10 ** 6)
input = lambda: sys.stdin.readline().rstrip()

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def cheese_check():
    for y in range(N):
        for x in range(M):
            if matrix[y][x] == 1:
                return False
    return True

def dfs(x, y):
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < M and 0 <= ny < N and not visited[ny][nx]:
            if matrix[ny][nx] != 0:
                matrix[ny][nx] += 1
            else:
                visited[ny][nx] = True
                dfs(nx, ny)

def remove_cheese():
    for y in range(N):
        for x in range(M):
            if matrix[y][x] >= 3:
                matrix[y][x] = 0
            elif matrix[y][x] > 0:
                matrix[y][x] = 1

N, M = map(int, input().split())
matrix = [list(map(int, input().split())) for _ in range(N)]
ans = 0
while True:
    visited = [[False] * M for _ in range(N)]
    if cheese_check():
        print(ans)
        break
    visited[0][0] = True
    dfs(0, 0)
    remove_cheese()
    ans += 1
