#include <iostream>
#include <queue>
using namespace std;

constexpr int MAX = 101;
constexpr int dx[] = { 0,0,-1,1 };
constexpr int dy[] = { -1,1,0,0 };

int N;
int map[MAX][MAX];
int domainMap[MAX][MAX]; // 입력받은 map에 영역별로 영역값을 할당한 새로운 map
int domainDistance[MAX][MAX]; // 거리 값을 계산하기 위한 별도의 map
int ans = 1000000;

bool chk(int x, int y) {
	return 0 <= x && x < N && 0 <= y && y < N;
}

void dfs(int x, int y, int domain) {
	domainMap[y][x] = domain;

	for (int i = 0; i < 4; i++) {
		int nextX = x + dx[i];
		int nextY = y + dy[i];

		if (chk(nextX, nextY)) {
			if (map[nextY][nextX] == 1 && domainMap[nextY][nextX] == 0) {
				dfs(nextX, nextY, domain);
			}
		}
	}
}

int setDomain() {
	int domainCount = 0;
	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			if (map[y][x] == 1 && domainMap[y][x] == 0) {
				dfs(x, y, ++domainCount);
			}
		}
	}

	return domainCount; // 영역이 몇 개인지 반환
}

int getDistance(int domain) {
	int distance = 1000000;

	queue<pair<int, int>> domainLocation; // domain 영역의 좌표값들을 담는다.

	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			if (domainMap[y][x] == domain) {
				domainLocation.push({ x, y });
				domainDistance[y][x] = 0; // 해당 영역 지점의 거리값을 0으로 설정
			}
			else domainDistance[y][x] = -1;
		}
	}

	while (!domainLocation.empty()) {
		int x = domainLocation.front().first;
		int y = domainLocation.front().second;
		domainLocation.pop();

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (chk(nextX, nextY)) {
				if (domainDistance[nextY][nextX] == -1) {
					domainDistance[nextY][nextX] = domainDistance[y][x] + 1;
					domainLocation.push({ nextX, nextY });
				}
			}
		}
	}

	for (int y = 0; y < N; y++) {
		for (int x = 0; x < N; x++) {
			if (map[y][x] && domainMap[y][x] != domain) {
				if (distance > domainDistance[y][x] - 1) distance = domainDistance[y][x] - 1;
			}
		}
	}

	return distance;
}

int main(void) {
	cin >> N;

	for (int y = 0; y < N; y++)
		for (int x = 0; x < N; x++)
			cin >> map[y][x];

	int domainCount = setDomain();

	for (int domain = 1; domain <= domainCount; domain++) {
		int distance = getDistance(domain);
		if (ans > distance) ans = distance;
	}

	cout << ans << "\n";

	return 0;
}