import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

def dfs(v):
    global result
    visited[v] = True
    traced.append(v)
    next = graph[v]
    if visited[next]:
        if next in traced:
            result += traced[traced.index(next):]
    else:
        dfs(next)

T = int(input())
for _ in range(T):
    N = int(input())
    graph = [0] + list(map(int, input().split()))
    visited = [False] * (N + 1)
    result = []
    for i in range(1, N + 1):
        if not visited[i]:
            traced = []
            dfs(i)
    print(N - len(result))