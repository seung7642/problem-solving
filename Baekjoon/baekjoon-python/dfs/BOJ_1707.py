import sys
sys.setrecursionlimit(100000)
input = sys.stdin.readline

def dfs(v, group):
    visited[v] = group
    for next in vertex[v]:
        if visited[next] == 0:
            if not dfs(next, -group):
                return False
        elif visited[v] == visited[next]:
            return False
    return True

K = int(input())
for _ in range(K):
    V, E = map(int, input().split())
    vertex = [[] for _ in range(V + 1)]
    visited = [0] * (V + 1)
    for _ in range(E):
        u, v = map(int, input().split())
        vertex[u].append(v)
        vertex[v].append(u)
    is_bipartite = True
    for i in range(1, V + 1):
        if visited[i] == 0:
            is_bipartite = dfs(i, 1)
            if not is_bipartite:
                break
    print("YES" if is_bipartite else "NO")