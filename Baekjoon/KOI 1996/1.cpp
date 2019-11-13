#include <bits/stdc++.h>
using namespace std;

constexpr int MAX_N = 26;

int N;
int cnt;
vector<int> a[MAX_N];
int visited[MAX_N][MAX_N];
int ans[MAX_N * MAX_N];
int dx[4] = { 1, -1, 0, 0 };
int dy[4] = { 0, 0,1,-1 };

int dfs(int x, int y, int cnt) {
    visited[y][x] = cnt;

    for (int i = 0; i < 4; ++i) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (0 <= nx && nx < N && 0 <= ny && ny < N) {
            if (a[ny][nx] == 1 && visited[ny][nx] == 0) {
                dfs(nx, ny, cnt);
            }
        }
    }

    return 0;
}

int main() {
    cin >> N;

    for (int i = 0; i < N; ++i) {
        string s;
        cin >> s;
        for (int j = 0; j < N; ++j) {
            a[i].push_back(s[j] - '0');
        }
    }

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (visited[i][j] == 0 && a[i][j] == 1)
                dfs(j, i, ++cnt);
        }
    }

    cout << cnt << "\n";

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            ans[visited[i][j]]++;
        }
    }

    sort(ans + 1, ans + cnt + 1);
    for (int i = 1; i <= cnt; ++i)
        cout << ans[i] << "\n";

    return 0;
}