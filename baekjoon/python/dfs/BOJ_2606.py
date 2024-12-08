edges = {}
for i in range(int(input())):
    edges[i + 1] = set()

for i in range(int(input())):
    v1, v2 = map(int, input().split())
    edges[v1].add(v2)
    edges[v2].add(v1)

visited = {1: True}


def dfs(node: int):
    for next in edges[node]:
        if next not in visited:
            visited[next] = True
            dfs(next)


dfs(1)
print(len(visited) - 1)
