#include <iostream>
#include <queue>
#include <tuple>
using namespace std;

char map[50][50]; // '.' : 비어있는 곳, '*' : 물, 'X' : 돌, 'D' : 비버, 'S' : 고슴도치
int distanceMap[50][50]; // 거리를 체크하기 위한 별도의 맵
int row, col;
int dx[] = { 0, 0, -1, 1 };
int dy[] = { -1, 1, 0, 0 };
queue<tuple<int, int, char>> q; // (x, y)와 물인지 고슴도치인지 담는다.
int ans;

bool rangeCheck(int x, int y) {
    return (0 <= x && x < col && 0 <= y && y < row);
}

bool isHedgehog() {
    queue<tuple<int, int, char>> tempQ = q;
    bool flag = false;

    while (!tempQ.empty()) {
        if (get<2>(tempQ.front()) == 'S') {
            flag = true;
            break;
        }
        tempQ.pop();
    }

    return flag;
}

void bfs() {
    while (!q.empty()) {
        if (!isHedgehog()) {
            cout << "KAKTUS\n";
            exit(0);
        }

        int x = get<0>(q.front());
        int y = get<1>(q.front());
        char kind = get<2>(q.front());
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (rangeCheck(nextX, nextY)) {
                if (kind == '*' && !(map[nextY][nextX] == 'X' || map[nextY][nextX] == 'D' || map[nextY][nextX] == '*')) { // 물
                    map[nextY][nextX] = '*';
                    q.push({ nextX, nextY, '*' });
                }

                if (kind == 'S' && !(map[nextY][nextX] == '*' || map[nextY][nextX] == 'X' || map[nextY][nextX] == 'S')) { // 고슴도치
                    distanceMap[nextY][nextX] = distanceMap[y][x] + 1;
                    if (map[nextY][nextX] == 'D') {
                        ans = distanceMap[nextY][nextX];
                        return;
                    }

                    map[nextY][nextX] = 'S';
                    q.push({ nextX, nextY, 'S' });
                }
            }
        }
    }
}

int main() {
    cin >> row >> col;

    tuple<int, int, char> hedgehogPosition;
    for (int y = 0; y < row; y++) {
        string str;
        cin >> str;
        for (int x = 0; x < col; x++) {
            map[y][x] = str[x];
            if (map[y][x] == '*') q.push({ x, y, '*' });
            if (map[y][x] == 'S') {
                hedgehogPosition = make_tuple(x, y, 'S');
            }
        }
    }

    q.push(hedgehogPosition);
    bfs();
    cout << ans << "\n";

    return 0;
}