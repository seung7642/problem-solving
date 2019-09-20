#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

constexpr int MAX = 300;
constexpr int dx[] = { -1, 1, -1, 1, -2, -2, 2, 2 }; // 나이트의 이동경로는 8가지
constexpr int dy[] = { -2, -2, 2, 2, -1, 1, -1, 1 };

int T, N;
int x, y;
int destinationX, destinationY;
int map[MAX][MAX];
bool visited[MAX][MAX];

int bfs(int x, int y) {
	queue<pair<int, int>> q;
	q.push({ x,y });
	visited[y][x] = true;

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int i = 0; i < 8; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (0 <= nextX && nextX < N && 0 <= nextY && nextY < N) {
				if (!visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					map[nextY][nextX] = map[y][x] + 1; // BFS에서 깊이를 확인하는 방법 !
					q.push({ nextX, nextY });

					if (nextX == destinationX && nextY == destinationY) {
						cout << map[nextY][nextX] << "\n";
						return 0;
					}
				}
			}
		}
	}

	return 0;
}

int main() {
	cin >> T;

	while (T--) {
		// 초기화
		memset(map, false, sizeof(map));
		memset(visited, false, sizeof(visited));

		cin >> N;
		cin >> x >> y >> destinationX >> destinationY;
		if (x == destinationX && y == destinationY) {
			cout << "0\n";
			continue;
		}

		bfs(x, y);
	}

	return 0;
}