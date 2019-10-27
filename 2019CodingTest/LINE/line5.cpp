// 입력 예)
// N : 9, M : 9
// destinationX : 2
// destinationY : 1
// result : 3 3
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

bool rangeCheck(int x, int y) {
    return 0 <= x && x < N && 0 <= y && y < M;
}

void bfs(int x, int y) { // 잡을 수 있는 최소 시간을 구한다.
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

            if (rangeCheck(nextX, nextY)) {
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
}

void dfs(int x, int y, int step) { // 깊이 == 최소시간을 가지는 경로의 갯수를 구한다.
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

        if (rangeCheck(nextX, nextY) && !visited[nextY][nextX]) {
            visited[nextY][nextX] = true;
            dfs(nextX, nextY, step + 1);
            visited[nextY][nextX] = false;
        }
    }
}

int main() {
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