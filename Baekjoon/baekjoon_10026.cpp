#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

constexpr int MAX = 100;
constexpr int dx[] = { 0, 0, -1, 1 };
constexpr int dy[] = { -1, 1, 0, 0 };

int N;
int mapForPublic[MAX][MAX];
int mapForPatient[MAX][MAX];
bool visited[MAX][MAX];

int bfs(int x, int y, int color, int map[][MAX], int domainCount) {
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
				if (map[nextY][nextX] == color && !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					q.push({ nextX,nextY });
				}
			}
		}
	}

	return 0;
}

int calDomainCount(int map[][MAX], int colorCount) {
	int totalDomainCount = 0;

	for (int color = 0; color < colorCount; color++) {
		int domainCount = 0;
		memset(visited, false, sizeof(visited));

		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == color && !visited[y][x]) {
					bfs(x, y, color, map, ++domainCount);
				}
			}
		}

		totalDomainCount += domainCount;
	}

	return totalDomainCount;
}

int main() {
	cin >> N;

	for (int i = 0; i < N; i++) {
		string input;
		cin >> input;

		for (int j = 0; j < N; j++) {
			if (input[j] == 'R') {
				mapForPublic[i][j] = 0;
				mapForPatient[i][j] = 0;
			}
			else if (input[j] == 'G') {
				mapForPublic[i][j] = 1;
				mapForPatient[i][j] = 0;
			}
			else if (input[j] == 'B') {
				mapForPublic[i][j] = 2;
				mapForPatient[i][j] = 1;
			}
		}
	}

	// 일반인 기준의 영역 갯수
	cout << calDomainCount(mapForPublic, 3) << " ";

	// 적록색약 기준의 영역 갯수
	cout << calDomainCount(mapForPatient, 2) << "\n";

	return 0;
}