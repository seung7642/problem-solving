import sys
sys.setrecursionlimit(10000)


def dfs(node: int):
    visited[node] = True
    for next in edges[node]:
        if not visited[next]:
            dfs(next)


N, M = map(int, input().split())
edges = [[] for _ in range(N + 1)]
visited = [False for _ in range(N + 1)]

for _ in range(M):
    v1, v2 = map(int, input().split())
    edges[v1].append(v2)
    edges[v2].append(v1)

cnt = 0
for i in range(1, N + 1):
    if not visited[i]:
        cnt += 1
        dfs(i)

print(cnt)
