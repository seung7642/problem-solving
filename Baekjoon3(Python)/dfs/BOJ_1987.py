import sys
sys.setrecursionlimit(10000)
input = sys.stdin.readline

R, C = map(int, input().split())
matrix = [list(input().rstrip()) for _ in range(R)]

ans = 0
alphabets = set()
dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

def dfs(x, y, cnt):
    global ans
    ans = max(ans, cnt)
    for i in range(4):
        nx, ny = x + dx[i], y + dy[i]
        if 0 <= nx < C and 0 <= ny < R:
            if not matrix[ny][nx] in alphabets:
                alphabets.add(matrix[ny][nx])
                dfs(nx, ny, cnt + 1)
                alphabets.remove(matrix[ny][nx])

alphabets.add(matrix[0][0])
dfs(0, 0, 1)
print(ans)
