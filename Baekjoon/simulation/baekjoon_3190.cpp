#include <iostream>
#include <queue>
using namespace std;

int N, K, L;
int map[101][101]; // 0: 미탐색, 1: 사과가 위치한 곳, 2: 뱀이 위치한 곳
queue<pair<int, char>> q;
queue<pair<int, int>> snake; // 뱀의 머리-몸통-꼬리 모두 관리한다.
int cnt;

bool rangeCheck(int x, int y) {
    return (1 <= x && x <= N && 1 <= y && y <= N);
}

void moveSnake(int x, int y) {
    if (map[y][x] == 1) { // 사과가 위치한 곳이면 몸길이+1
        snake.push({ x, y });
        map[y][x] = 2;
    }
    else {
        map[snake.front().second][snake.front().first] = 0;
        snake.pop();

        snake.push({ x, y });
        map[y][x] = 2;
    }
}

void solution(int x, int y, int direction) {
    snake.push({ x, y });
    map[y][x] = 2;

    while (true) {
        if (!q.empty() && q.front().first == cnt) {
            if (q.front().second == 'L') {
                direction -= 1;
                if (direction < 0) direction = 3;
            }
            else if (q.front().second == 'D') {
                direction = (direction + 1) % 4;
            }

            q.pop();
        }

        if (direction == 0) { // 북(0), 동(1), 남(2), 서(3)
            y -= 1;
            if (!rangeCheck(x, y) || map[y][x] == 2) { // 벽을 만나거나 || 자기자신의 몸을 만나거나
                break;
            }

            moveSnake(x, y);
        }
        else if (direction == 1) {
            x += 1;
            if (!rangeCheck(x, y) || map[y][x] == 2) {
                break;
            }

            moveSnake(x, y);
        }
        else if (direction == 2) {
            y += 1;
            if (!rangeCheck(x, y) || map[y][x] == 2) {
                break;
            }

            moveSnake(x, y);
        }
        else if (direction == 3) {
            x -= 1;
            if (!rangeCheck(x, y) || map[y][x] == 2) {
                break;
            }

            moveSnake(x, y);
        }

        cnt += 1;
    }
}

int main() {
    // 조건에 따른 탐색
    cin >> N >> K;

    for (int i = 0, y, x; i < K; i++) {
        cin >> y >> x;
        map[y][x] = 1; // 사과가 위치한 곳은 1
    }

    cin >> L;
    for (int i = 0; i < L; i++) {
        int a;
        char c;
        cin >> a >> c;
        q.push({ a, c });
    }

    solution(1, 1, 1);
    cout << cnt + 1 << "\n";

    return 0;
}