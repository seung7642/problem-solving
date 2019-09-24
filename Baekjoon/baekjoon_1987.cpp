#include <iostream>
#include <string>
using namespace std;

constexpr int MAX = 20;
constexpr int posX[4] = { 1, 0, -1, 0 };
constexpr int posY[4] = { 0, 1, 0 , -1 };

char map[MAX][MAX];
bool alpha[26];
int row, col;
int mMax = 1;

// DFS + 백트래킹
void dfs(int x, int y, int step) {
    if (alpha[map[y][x] - 'A']) {
        if (step > mMax) mMax = step;
    }

    alpha[map[y][x] - 'A'] = true;

    for (int i = 0; i < 4; i++) {
        int nextX = x + posX[i];
        int nextY = y + posY[i];

        if (0 <= nextX && nextX < col && 0 <= nextY && nextY < row) {
            int idx = int(map[nextY][nextX]) - 'A';

            if (!alpha[idx]) {
                alpha[idx] = true;
                dfs(nextX, nextY, step + 1);
                alpha[idx] = false;
            }
        }
    }
}

int main() {
    cin >> row >> col;

    cin.ignore();
    for (int i = 0; i < row; i++)
        cin >> map[i];

    dfs(0, 0, 1);
    cout << mMax;

    return 0;
}