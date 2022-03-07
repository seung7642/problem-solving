import sys
sys.setrecursionlimit(10 ** 6)
input = sys.stdin.readline

def dfs(node):
    for next, cost in graph[node]:
        if visited[next] == 0:
            visited[next] = visited[node] + cost
            dfs(next)

V = int(input())
graph = [[] for _ in range(V + 1)]
visited = [0] * (V + 1)
for _ in range(V):
    arr = list(map(int, input().split()))
    for i in range(1, len(arr) - 2, 2):
        graph[arr[0]].append((arr[i], arr[i + 1]))

first_start_node = 1
dfs(first_start_node)
visited[first_start_node] = 0

second_start_node = visited.index(max(visited))
visited = [0 for _ in range(V + 1)]
dfs(second_start_node)
visited[second_start_node] = 0

print(max(visited))