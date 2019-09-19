#include <iostream>
#include <queue>
using namespace std;

constexpr int MAX = 100;

int N, M, H;

int map[MAX][MAX][MAX];
int chk[MAX][MAX][MAX];
int dz[2] = { -1, 1 };
int dx[4] = { 0, 0, -1, 1 };
int dy[4] = { -1, 1, 0, 0 };

void bfs(queue<pair<int, pair<int, int>>>& q) {
	// 매개변수로 넘어온 큐에는 다수의 시작 정점들이 담겨져있다.

	while (!q.empty()) {
		int z = q.front().first;
		int x = q.front().second.first;
		int y = q.front().second.second;
		q.pop();

		for (int k = 0; k < 4; ++k) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if (0 <= nx && nx < M && 0 <= ny && ny < N) {
				// 아직 방문하지 않았고 && 익지 않은 토마토라면
				if (chk[z][ny][nx] == 0 && map[z][ny][nx] == 0) {
					q.push({ z, { nx, ny } });
					chk[z][ny][nx] = chk[z][y][x] + 1; // 거리를 1씩 증가시킨다.
				}
			}
		}

		for (int k = 0; k < 2; ++k) {
			int nz = z + dz[k];

			if (0 <= nz && nz < H) {
				if (chk[nz][y][x] == 0 && map[nz][y][x] == 0) {
					q.push({ nz, {x, y} });
					chk[nz][y][x] = chk[z][y][x] + 1;
				}
			}
		}
	}
}

int main()
{
	ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);

	cin >> M >> N >> H;

	// 이 문제의 포인트는 시작하는 정점이 1개가 아니라, 여러 개라는 것.
	// 다수의 시작 정점을 공평하게 1번씩 너비 우선 탐색(BFS)하는 것이 포인트

	queue<pair<int, pair<int, int>>> q;
	for (int z = 0; z < H; ++z) {
		for (int y = 0; y < N; ++y) {
			for (int x = 0; x < M; ++x) {
				cin >> map[z][y][x];
				if (map[z][y][x] == 1) {
					// 익은 토마토가 시작 정점이 되고, 다수의 시작 정점을 미리 큐에 담아놓는다.
					q.push({ z, { x, y } });
				}
			}
		}
	}

	// bfs() 함수를 실행하면 chk 배열이 세팅이 된다.
	bfs(q);

	int ans = 0;
	for (int z = 0; z < H; ++z) {
		for (int y = 0; y < N; ++y) {
			for (int x = 0; x < M; ++x) {
				ans = (ans < chk[z][y][x] ? chk[z][y][x] : ans);
			}
		}
	}

	for (int z = 0; z < H; ++z) {
		for (int y = 0; y < N; ++y) {
			for (int x = 0; x < M; ++x) {
				// 익지 않은 토마토 && 방문하지 않은게 1개라도 있다면
				if (map[z][y][x] == 0 && chk[z][y][x] == 0) {
					ans = -1;
				}
			}
		}
	}

	cout << ans << "\n";

	return 0;
}