#include <iostream>
#include <queue>
#include <cstring> // memset을 위한 헤더파일
using namespace std;

struct Point {
	int x;
	int y;

	Point() {}
	Point(int x, int y) : x(x), y(y) {}
};
int col, row;
int map[8][8];
Point virusPoint[64]; // 바이러스 공간(2) 좌표값을 담는다.
Point safetyPoint[64]; // 안전한 공간(0) 좌표값을 담는다.
int virusCount; // 바이러스 공간(2) 갯수
int safetyCount; // 안전한 공간(0)의 갯수
int maxSafety;
const int posX[4] = { 1, 0, -1, 0 };
const int posY[4] = { 0, 1, 0, -1 };

int calSafetyCount(int map[8][8]) {
	int safetyCount = 0;

	for (int y = 0; y < row; y++)
		for (int x = 0; x < col; x++)
			if (map[y][x] == 0) safetyCount += 1;
	return safetyCount;
}

// pos : 세운 벽돌의 수
// idx : 벽돌을 세우기 위한 안전한 공간의 위치 인덱스값
void bruteForce(int pos, int idx) {
	if (pos == 3) { // 3개의 벽돌을 세웠다면:
		int tempMap[8][8];
		bool visited[8][8];
		memset(visited, false, sizeof(visited));

		for (int y = 0; y < 8; y++)
			for (int x = 0; x < 8; x++)
				tempMap[y][x] = map[y][x];

		for (int i = 0; i < virusCount; i++) {
			queue<Point> q;
			q.push(Point{ virusPoint[i].x, virusPoint[i].y });

			while (!q.empty()) {
				int x = q.front().x;
				int y = q.front().y;
				q.pop();

				for (int pos = 0; pos < 4; pos++) {
					int nextX = x + posX[pos];
					int nextY = y + posY[pos];

					if (0 <= nextX && nextX < col && 0 <= nextY && nextY < row) {
						if (tempMap[nextY][nextX] == 0) {
							tempMap[nextY][nextX] = 2;
							q.push(Point{ nextX, nextY });
						}
					}
				}
			}
		}

		int cnt = calSafetyCount(tempMap); // 3개의 벽을 세운 뒤의 안전한 공간의 갯수를 확인한다.
		if (maxSafety < cnt) maxSafety = cnt;
	}
	else {
		for (int i = idx; i < safetyCount; i++) {
			Point point = safetyPoint[i];  // safetyPoint 배열에 차례대로 안전한 공간의 좌표값이 들어있다.
			map[point.y][point.x] = 1; // 해당 공간에 벽돌을 세운다.
			bruteForce(pos + 1, i + 1);
			map[point.y][point.x] = 0; // 원상복구시킨다.
		}
	}
}

int main() {
	cin >> row >> col;

	for (int y = 0; y < row; y++)
		for (int x = 0; x < col; x++) {
			cin >> map[y][x];

			if (map[y][x] == 2)
				virusPoint[virusCount++] = Point(x, y);
			else if (map[y][x] == 0)
				safetyPoint[safetyCount++] = Point(x, y);
		}

	bruteForce(0, 0);
	cout << maxSafety;
	return 0;
}