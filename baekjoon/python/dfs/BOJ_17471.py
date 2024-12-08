from collections import deque
import sys
input = lambda: sys.stdin.readline().rstrip()

def bfs(group):
    q = deque()
    check = [False for _ in range(N)]
    q.append(group[0])
    check[group[0]] = True

    cnt, sum = 1, 0
    while q:
        temp = q.popleft()
        sum += population[temp]
        for i in graph[temp]:
            # i는 인접한 값인데, 그 값이 g배열 안에 있고 방문한 적이 없다면 계속 진행이 가능합니다.
            if i in group and not check[i]:
                check[i] = True
                cnt += 1
                q.append(i)

    if cnt == len(group):
        return sum
    else:
        return False

def combination(depth, num, end):
    global ans

    # 원하는 구역의 개수만큼 도달했을 때
    if depth == end:
        g1, g2 = deque(), deque()

        # 방문했으면 g1그룹으로, 방문하지 않았으면 g2그룹으로 넣어줍니다.
        for i in range(N):
            if visited[i]:
                g1.append(i)
            else:
                g2.append(i)

        result1 = bfs(g1) # 현재 조합으로 나눈 선거구가 올바른지 확인합니다. (각 선거구의 지역들은 인접해있어야 합니다.)
        if not result1:
            return

        result2 = bfs(g2) # 현재 조합으로 나눈 선거구가 올바른지 확인합니다. (각 선거구의 지역들은 인접해있어야 합니다.)
        if not result2:
            return

        ans = min(ans, abs(result1 - result2))
        return

    for i in range(num, N):
        if not visited[i]:
            visited[i] = True
            combination(depth + 1, i, end)
            visited[i] = False

N = int(input())
population = list(map(int, input().split()))
graph = [[] for _ in range(N)]
visited = [False] * (N)
for i in range(N):
    arr = list(map(int, input().split()))
    for v in arr[1:]:
        graph[i].append(v - 1)

ans = sys.maxsize
# 선거구가 2개로 나누어지기 때문에 N//2까지만 확인하면 됩니다. 조합 nCm 에서 6C2 == 6C4 와 같기 때문입니다.
# A 선거구의 지역 수가 1개일때의 조합, 2개일때의 조합, 3개일때의 조합, ... 을 구합니다.
for i in range(1, N // 2 + 1):
    visited = [False for _ in range(N)]
    combination(0, 0, i)

if ans == sys.maxsize:
    print(-1)
else:
    print(ans)