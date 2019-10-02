#include <iostream>
using namespace std;

constexpr int dx[] = { 0, 1, 0, -1 }; // 북, 동, 남, 서
constexpr int dy[] = { -1, 0, 1, 0 };

int map[50][50]; // 0: 청소안됨, 1: 벽, 2: 청소됨
int row, col;
int r, c, d;
int cnt;


void dfs(int x, int y, int direction) {
    if (map[y][x] == 0) { // 현재 위치를 청소한다.
        map[y][x] = 2;
        cnt++;
    }

    for (int i = 0; i < 4; i++) { // 네 방향의 청소를 진행한다.
        int nextDirection = (direction + 3 - i) % 4; // 0->3, 1->0, 2->1, 3->2
        int nextX = x + dx[nextDirection];
        int nextY = y + dy[nextDirection];

        if (0 <= nextX && nextX < col && 0 <= nextY && nextY < row) {
            if (map[nextY][nextX] == 0) {
                dfs(nextX, nextY, nextDirection);
            }
        }
    }

    int nextX = x + dx[(direction + 2) % 4]; // 현재 바라보는 방향을 기준으로 후진
    int nextY = y + dy[(direction + 2) % 4]; // 0->2, 1->3, 2->0, 3->1
    if (map[nextY][nextX] == 1) {
        cout << cnt << "\n";
        exit(0);
    }
    dfs(nextX, nextY, direction);
}

int main() {
    cin >> row >> col;
    cin >> r >> c; // r: 북쪽으로부터 떨어진 칸의 개수, c: 서쪽으로부터 떨어진 칸의 개수
    cin >> d;

    for (int y = 0; y < row; y++)
        for (int x = 0; x < col; x++)
            cin >> map[y][x];

    dfs(c, r, d);

    return 0;
}