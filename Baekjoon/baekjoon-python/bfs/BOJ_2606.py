from collections import deque


def bfs(start: int) -> int:
    visited = [0 for _ in range(N + 1)]
    visited[start] = True
    q = deque()
    q.append(start)
    cnt = 0
    while q:
        cur = q.popleft()
        for next in edges[cur]:
            if visited[next]:
                continue
            visited[next] = True
            q.append(next)
            cnt += 1
    return cnt


N = int(input())
M = int(input())
edges = {}
for i in range(N):
    edges[i + 1] = set()

for i in range(M):
    v1, v2 = map(int, input().split())
    edges[v1].add(v2)
    edges[v2].add(v1)

print(bfs(1))
