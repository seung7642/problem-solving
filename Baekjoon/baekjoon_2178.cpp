#include <iostream>
#include <queue>
#include <string>
using namespace std;

constexpr int MAX = 100;

int N, M;
int map[MAX][MAX]; // input으로 들어오는 맵을 저장.
int ans[MAX][MAX];
bool chk[MAX][MAX];
int dx[4] = { 0, 0, 1, -1 };
int dy[4] = { 1, -1, 0, 0 };

void bfs() {
	queue<pair<int, int>> q;
	q.push({ 0, 0 });
	chk[0][0] = 1; ans[0][0] = 1;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int k = 0; k < 4; ++k) {
			int nx = dx[k] + x;
			int ny = dy[k] + y;

			if (0 <= nx && nx < N && 0 <= ny && ny < M) {
				if (map[nx][ny] == 1 && chk[nx][ny] == 0) {
					q.push({ nx, ny });
					ans[nx][ny] = ans[x][y] + 1;
					chk[nx][ny] = 1; // 탐색한 곳이라 표시
				}
			}
		}
	}
}

int main() {
	cin.sync_with_stdio(false); cin.tie(NULL);
	cin >> N >> M;
	cin.ignore();
	string str;
	for (int y = 0; y < N; ++y) {
		getline(cin, str);
		for (int x = 0; x < M; ++x) {
			map[y][x] = str[x] - '0';
		}
	}

	bfs();

	cout << ans[N - 1][M - 1] << "\n";

	return 0;
}