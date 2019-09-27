#include <iostream>
using namespace std;

struct Point {
    int x;
    int y;
    Point() {}
    Point(int _x, int _y) : x(_x), y(_y) {}
};
int map[9][9];
bool visited[9][9];
Point emptySpace[9 * 9]; // 스도쿠 맵에서 비어있는 곳의 좌표값을 저장한다.
int emptyCount;
bool flag; // 스도쿠 답이 여러 개가 나올 수 있는데, 최초로 1개의 답을 찾으면 그만 찾도록 설정한다.

void dfs(int node) {
    if (node == emptyCount) {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                cout << map[y][x] << " ";
            }
            cout << "\n";
        }
        flag = true;
    }

    int x = emptySpace[node].x;
    int y = emptySpace[node].y;

    // 현재 비어있는 좌표값을 기준으로 행, 열, 도메인에 없는 값을 찾는다.
    bool row[10] = { 0, };
    bool col[10] = { 0, };
    bool domain[10] = { 0, };

    for (int i = 0; i < 9; i++) // 행
        row[map[y][i]] = true;

    for (int i = 0; i < 9; i++)
        col[map[i][x]] = true;

    for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
            domain[map[y / 3 * 3 + i][x / 3 * 3 + j]] = true;

    for (int i = 1; i < 10; i++) {
        if (!(row[i] || col[i] || domain[i] || flag)) { // 해당하는 숫자가 행, 열, 도메인에 없다면
            map[y][x] = i;
            dfs(node + 1);
            map[y][x] = 0;
        }
    }
}

int main() {
    for (int y = 0; y < 9; y++) {
        for (int x = 0; x < 9; x++) {
            cin >> map[y][x];
            if (map[y][x] == 0) emptySpace[emptyCount++] = Point{ x, y };
        }
    }

    dfs(0);

    return 0;
}