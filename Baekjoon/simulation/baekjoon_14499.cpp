#include <iostream>
using namespace std;
int n, m;
int x, y;
int k;
int mat[20][20];
int dice[7] = { 0, 0, 0, 0, 0, 0, 0 };
int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { 1, -1, 0, 0 };
bool safe(int x, int y) {
    return 0 <= x && x < n && 0 <= y && y < m;
}
int main() {
    cin >> n >> m >> x >> y >> k;
    for (int i = 0; i < n; i++)
        for (int j = 0; j < m; j++)
            cin >> mat[i][j];
    for (int i = 0; i < k; i++) {
        int order;
        cin >> order;
        order--;
        int nx = x + dx[order];
        int ny = y + dy[order];
        if (!safe(nx, ny))
            continue;
        int ndice[7] = { 0, };
        if (order == 0) {
            ndice[3] = dice[1];
            ndice[1] = dice[4];
            ndice[4] = dice[6];
            ndice[6] = dice[3];
            ndice[2] = dice[2];
            ndice[5] = dice[5];
        }
        if (order == 1) {
            ndice[1] = dice[3];
            ndice[3] = dice[6];
            ndice[6] = dice[4];
            ndice[4] = dice[1];
            ndice[2] = dice[2];
            ndice[5] = dice[5];
        }
        if (order == 2) {
            ndice[1] = dice[5];
            ndice[5] = dice[6];
            ndice[6] = dice[2];
            ndice[2] = dice[1];
            ndice[3] = dice[3];
            ndice[4] = dice[4];
        }
        if (order == 3) {
            ndice[1] = dice[2];
            ndice[2] = dice[6];
            ndice[6] = dice[5];
            ndice[5] = dice[1];
            ndice[3] = dice[3];
            ndice[4] = dice[4];
        }
        if (mat[nx][ny] == 0) {
            mat[nx][ny] = ndice[6];
        }
        else {
            ndice[6] = mat[nx][ny];
            mat[nx][ny] = 0;
        }
        cout << ndice[1] << "\n";
        for (int i = 0; i < 7; i++)
            dice[i] = ndice[i];
        x = nx;
        y = ny;
    }
    return 0;
}