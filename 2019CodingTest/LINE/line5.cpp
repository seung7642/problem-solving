#include <iostream>
#include <queue>
using namespace std;

constexpr int MAX = 25;
constexpr int dx[] = { 0, 0, -1, 1 };
constexpr int dy[] = { -1, 1, 0, 0 };

int N, M;
int destinationX, destinationY;
int minimumTime;
int cnt;
int map[MAX][MAX];
bool visited[MAX][MAX];

// 잡을 수 있는 최소 시간을 구한다.
int bfs(int x, int y) {
    queue<pair<int, int>> q;
    q.push({ x, y });
    visited[y][x] = true;

    int cnt = 0, minimumTime = 100;
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M) {
                if (!visited[nextY][nextX]) {
                    visited[nextY][nextX] = true;
                    map[nextY][nextX] = map[y][x] + 1; // 깊이를 누적시킨다.
                    q.push({ nextX, nextY });
                    if (nextX == destinationX && nextY == destinationY)
                        if (minimumTime >= map[nextY][nextX]) {
                            minimumTime = map[nextY][nextX];
                        }
                }
            }
        }
    }

    return 0;
}

// 깊이 == 최소시간을 가지는 경로의 갯수를 구한다.
void dfs(int x, int y, int step) {
    // 재귀 종료 조건: (0, 0)으로 출발해 목적지 좌표로 도착했다면
    if (step == minimumTime) {
        if (x == destinationX && y == destinationY) {
            cnt++;
            return;
        }
        else return;
    }

    visited[y][x] = true;
    for (int i = 0; i < 4; i++) {
        int nextX = x + dx[i];
        int nextY = y + dy[i];

        if (!visited[nextY][nextX]) {
            visited[nextY][nextX] = true;
            dfs(nextX, nextY, step + 1);
            visited[nextY][nextX] = false;
        }
    }
}

int main(void) {
    cin >> N >> M;
    cin >> destinationX >> destinationY;

    bfs(0, 0);
    cout << map[destinationY][destinationX] << "\n";

    minimumTime = map[destinationY][destinationX];
    memset(map, false, sizeof(map));
    memset(visited, false, sizeof(visited));

    dfs(0, 0, 0);
    cout << cnt << "\n";

    return 0;
}