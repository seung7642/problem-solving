from sys import stdin
N, M = map(int, stdin.readline().split())
board = []

for _ in range(N):
    board.append(list(stdin.readline()))

# 배열을 int형으로 선언합니다.
board[0][0] = 1

dx = [0, 0, -1, 1]
dy = [-1, 1, 0, 0]

q = [[0, 0]]
while q:
    x, y = q[0][0], q[0][1]
    del q[0]

    for i in range(4):
        nx = x + dx[i]
        ny = y + dy[i]

        if 0 <= nx and nx < M and 0 <= ny and ny < N:
            if board[ny][nx] == '1':
                q.append([nx, ny])
                board[ny][nx] = board[y][x] + 1

print(board[N - 1][M - 1])
