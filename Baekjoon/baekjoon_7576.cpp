#include <iostream>
#include <queue>
using namespace std;

constexpr int MAX = 1000;

int N, M;

int map[MAX][MAX];
int chk[MAX][MAX];
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

void bfs(queue<pair<int, int>>& q) {
	// 매개변수로 넘어온 큐에는 다수의 시작 정점들이 담겨져있다.

	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();

		for (int k = 0; k < 4; ++k) {
			// 상하좌우 탐색
			int nx = x + dx[k];
			int ny = y + dy[k];

			if (0 <= nx && nx < M && 0 <= ny && ny < N) {
				// 아직 방문하지 않았고 && 익지 않은 토마토라면
				if (chk[ny][nx] == 0 && map[ny][nx] == 0) {
					q.push({ nx, ny });
					chk[ny][nx] = chk[y][x] + 1; // 거리를 1씩 증가시킨다.
				}
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);

	cin >> M >> N;

	// 이 문제의 포인트는 시작하는 정점이 1개가 아니라, 여러 개라는 것.
	// 다수의 시작 정점을 공평하게 1번씩 너비 우선 탐색(BFS)하는 것이 포인트

	queue<pair<int, int>> q;
	for (int y = 0; y < N; ++y) {
		for (int x = 0; x < M; ++x) {
			cin >> map[y][x];
			if (map[y][x] == 1) {
				// 익은 토마토가 시작 정점이 되고, 다수의 시작 정점을 미리 큐에 담아놓는다.
				q.push({ x, y });
			}
		}
	}

	// bfs() 함수를 실행하면 chk 배열이 세팅이 된다.
	bfs(q);

	int ans = 0;
	for (int y = 0; y < N; ++y) {
		for (int x = 0; x < M; ++x) {
			ans = (ans < chk[y][x] ? chk[y][x] : ans);
		}
	}

	for (int y = 0; y < N; ++y) {
		for (int x = 0; x < M; ++x) {
			// 익지 않은 토마토 && 방문하지 않은게 1개라도 있다면
			if (map[y][x] == 0 && chk[y][x] == 0) {
				ans = -1;
			}
		}
	}

	cout << ans << "\n";

	return 0;
}