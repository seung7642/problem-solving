#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

constexpr short ROW = 12;
constexpr short COL = 6;
constexpr short directionX[] = { 0, 0, -1, 1 };
constexpr short directionY[] = { -1, 1, 0, 0 };

char map[ROW][COL + 1];
int chain;

int labelingMap[ROW][COL] = { 0, }; // 영역을 저장할 별도의 맵
bool visited[ROW][COL] = { 0, };
int labelCount[12 * 6 + 1] = { 0, }; // 영역은 1번부터 시작하기 때문에 +1

struct Point {
    short x;
    short y;

    Point() {}
    Point(int _x, int _y) : x(_x), y(_y) {}
};

bool rangeCheck(int x, int y) {
    return (0 <= x && x < COL && 0 <= y && y < ROW);
}

// 1번의 BFS 탐색을 이용해 영역을 나눈다.
// 만약 같은 색이라도 다른 영역에 있다면 서로를 구분한다.
void bfs(int x, int y, int label) {
    char color = map[y][x];
    queue<Point> q;
    q.push({ x, y });
    visited[y][x] = true;
    labelingMap[y][x] = label;
    labelCount[label]++;

    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];

            if (rangeCheck(nextX, nextY)) {
                if (!visited[nextY][nextX] && map[nextY][nextX] == color) {
                    visited[nextY][nextX] = true;
                    labelingMap[nextY][nextX] = label;
                    labelCount[label]++;
                    q.push(Point{ nextX, nextY });
                }
            }
        }
    }
}

int setLabelingMap() {
    int label = 1;
    for (int y = 0; y < ROW; y++) {
        for (int x = 0; x < COL; x++) {
            if (!visited[y][x] && map[y][x] != '.') {
                bfs(x, y, label++);
            }
        }
    }

    return label;
}

void gravityPuyo() {
    for (int i = 0; i < COL; i++) {
        int target = 1, base = 0; // 목표지점, 현재 지점
        while (base + target < ROW) {
            if (map[base][i] != '.') // 공백 지점 탐색
                base++;
            else if (map[target + base][i] == '.') // 바꿔야할 지점 탐색
                target++;
            else  { // 현재 지점이 공백 이면서 바꿔야 할 지점이 Puyo인 경우
                swap(map[base][i], map[base + target][i]);
                base++;
                target = 1;
            }
        }
    }
}

void burstPuyo(int label) { // 현재 맵에서 4개 이상의 뿌요를 터뜨린다.
    for (int i = 1; i <= label; i++) {
        if (labelCount[i] >= 4) {
            for (int y = 0; y < ROW; y++) {
                for (int x = 0; x < COL; x++) {
                    if (labelingMap[y][x] == i)
                        map[y][x] = '.';
                }
            }
        }
    }
}

bool hasBurstPuyo(int label) { // 현재 맵에서 터뜨릴 수 있는 뿌요가 있는지 확인한다.
    for (int i = 1; i <= label; i++)
        if (labelCount[i] >= 4) return true;

    return false;
}

int main() {
    for (int i = ROW - 1; i >= 0; i--)
        cin >> map[i];

    while (true) {
        // visited, labelingMap, labelCount 3개의 변수를 초기화한다.
        memset(visited, false, sizeof(visited));
        memset(labelingMap, 0, sizeof(labelingMap));
        memset(labelCount, 0, sizeof(labelCount));

        int label = setLabelingMap();

        if (!hasBurstPuyo(label)) break; // 터뜨릴 수 있는 뿌요가 없다면 루프 종료

        burstPuyo(label); // 4개 이상의 뿌요를 터뜨린다.

        gravityPuyo(); // map을 refresh한다. (중력에 따라 뿌요를 아래로 내릴 수 있다면 내린다.)

        chain += 1; // 연쇄 + 1
    }

    cout << chain << "\n";

    return 0;
}