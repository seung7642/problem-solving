import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

def dfs(node, weight):
    visited[node] = True
    for next, cost in tree[node]:
        if visited[next] == False:
            weight[next] = weight[node] + cost
            dfs(next, weight)

N = int(input())
tree = [[] for _ in range(N + 1)]
for _ in range(N - 1):
    u, v, cost = map(int, input().split())
    tree[u].append((v, cost))
    tree[v].append((u, cost))

weight = [0] * (N + 1) # 배열 생성과 동시에 0으로 초기화
visited = [False] * (N + 1)
dfs(1, weight)

start_node = weight.index(max(weight))
weight = [0 for _ in range(N + 1)] # 배열 값 초기화
visited = [False for _ in range(N + 1)]
dfs(start_node, weight)
print(max(weight))