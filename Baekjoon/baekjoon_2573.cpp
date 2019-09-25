#include <iostream>
#include <cstring>
using namespace std;

constexpr int MAX = 300;
constexpr int dx[] = { 0,0,-1,1 };
constexpr int dy[] = { -1,1,0,0 };

int row, col;
int map[MAX][MAX];
int melt[MAX][MAX]; // 녹일 빙하량을 저장한다.
bool visited[MAX][MAX];

// 빙산을 녹이기 위한 함수
void setIceberg() {
	for (int y = 1; y < row - 1; y++) {
		for (int x = 1; x < col - 1; x++) {
			int countZero = 0;
			for (int pos = 0; pos < 4; pos++) {
				if (map[y + dy[pos]][x + dx[pos]] == 0) melt[y][x]++;
			}
		}
	}
}

// 빙산이 다 녹았는지 확인하기 위한 함수
bool isMelted() {
	bool flag = true;

	for (int y = 1; y < row - 1; y++) {
		for (int x = 1; x < col - 1; x++) {
			if (map[y][x] != 0) flag = false; // 0이 아닌 값이 하나라도 있다면 다 녹지 않았음을 의미한다.
		}
	}

	if (flag) return true;
	else return false;
}

// 영역이 몇 개인지 구하기 위한 DFS 탐색함수
void dfs(int x, int y) {
	visited[y][x] = true;

	for (int i = 0; i < 4; i++) {
		int nextX = x + dx[i];
		int nextY = y + dy[i];

		if (0 <= nextX && nextX < col && 0 <= nextY && nextY < row) {
			if (!visited[nextY][nextX] && map[nextY][nextX] != 0) {
				visited[nextY][nextX] = true;
				dfs(nextX, nextY);
			}
		}
	}
}



int main(void) {
	cin >> row >> col;

	for (int i = 0; i < row; i++)
		for (int j = 0; j < col; j++)
			cin >> map[i][j];

	int yearCount = 0;
	while (true) {
		yearCount++;
		memset(visited, false, sizeof(visited));
		memset(melt, false, sizeof(melt));

		setIceberg();
		for (int y = 0; y < row; y++) {
			for (int x = 0; x < col; x++) {
				if (map[y][x] > melt[y][x]) map[y][x] -= melt[y][x];
				else map[y][x] = 0;
			}
		}

		if (isMelted()) { // 빙산이 다 녹았다면
			yearCount = 0;
			break;
		}

		int domainCount = 0;
		for (int y = 0; y < row; y++) { // 영역이 몇 개인지 구한다.
			for (int x = 0; x < col; x++) {
				if (!visited[y][x] && map[y][x] != 0) {
					dfs(x, y);
					domainCount++;
				}
			}
		}

		if (domainCount >= 2) break;
	}

	cout << yearCount << "\n";

	return 0;
}