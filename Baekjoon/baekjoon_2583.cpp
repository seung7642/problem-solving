#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

constexpr int MAX = 101;

struct Point {
	int x, y;
	Point() {}
	Point(int _x, int _y) : x(_x), y(_y) {}
};
int row, col, k;
int map[MAX][MAX];
bool visited[MAX][MAX];
int domainCount;                // 영역의 갯수를 저장한다.
vector<int> domainSize(MAX);    // 영역별 크기를 저장한다.
const int dx[] = { 0, 0,-1,1 };
const int dy[] = { -1,1,0,0 };

void bfs(int x, int y, int domainCount) {
	queue<Point> q;
	q.push(Point{ x, y });
	visited[y][x] = true;
	domainSize[domainCount] += 1;

	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (0 <= nextX && nextX < col && 0 <= nextY && nextY < row) {
				if (!map[nextY][nextX] && !visited[nextY][nextX]) {
					visited[nextY][nextX] = true;
					domainSize[domainCount] += 1;
					q.push(Point{ nextX, nextY });
				}
			}
		}
	}
}

int main() {
	cin >> row >> col >> k;

	for (int i = 0, x1, y1, x2, y2; i < k; i++) {
		cin >> x1 >> y1 >> x2 >> y2;
		Point p1{ x1, y1 };

		for (int y = y1; y < y2; y++) {
			for (int x = x1; x < x2; x++) {
				map[y][x] = 1;
			}
		}
	}

	for (int y = 0; y < row; y++) {
		for (int x = 0; x < col; x++) {
			if (!map[y][x] && !visited[y][x]) {
				bfs(x, y, ++domainCount);
			}
		}
	}

	sort(domainSize.begin(), domainSize.end());
	cout << domainCount << "\n";
	for (int i = 1; i < 101; i++) {
		if (domainSize[i] != 0)
			cout << domainSize[i] << " ";
	}

	return 0;
}