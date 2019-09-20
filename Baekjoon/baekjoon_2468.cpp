#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

constexpr int MAX = 100;
constexpr int dx[] = { 0, 0, -1,1 };
constexpr int dy[] = { -1,1,0,0 };

int N;
int map[MAX][MAX];
int applyMap[MAX][MAX];
bool visited[MAX][MAX];
int maxDomainCount;

void bfs(int x, int y, int domainCount) {
	queue<pair<int, int>> q;
	q.push({ x, y });
	visited[y][x] = true;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
				if (!applyMap[nextY][nextX] && !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					q.push({ nextX, nextY });
				}
			}
		}
	}
}

int main() {
	cin >> N;

	for (int i = 0; i < N; i++)
		for (int j = 0; j < N; j++)
			cin >> map[i][j];

	for (int height = 0; height <= 100; height++) {
		// 초기화
		memset(applyMap, false, sizeof(applyMap));
		memset(visited, false, sizeof(visited));

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] <= height) {
					applyMap[i][j] = 1;
				}
			}
		}

		int domainCount = 0;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (!applyMap[y][x] && !visited[y][x]) {
					bfs(x, y, ++domainCount);
				}
			}
		}

		if (maxDomainCount < domainCount)
			maxDomainCount = domainCount;
	}

	cout << maxDomainCount << "\n";

	return 0;
}