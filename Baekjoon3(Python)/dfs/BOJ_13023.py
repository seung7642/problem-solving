import sys
sys.setrecursionlimit(10 ** 6)
input = lambda: sys.stdin.readline().rstrip()

def dfs(v, depth):
    if depth == 4:
        print(1)
        exit()
    for i in graph[v]:
        if not visited[i]:
            visited[i] = True
            dfs(i, depth + 1)
            visited[i] = False

N, M = map(int, input().split())
graph = [[] for _ in range(N)]
visited = [False] * N
for _ in range(M):
    a, b = map(int, input().split())
    graph[a].append(b)
    graph[b].append(a)

for i in range(N):
    visited[i] = True
    dfs(i, 0)
    visited[i] = False

print(0)