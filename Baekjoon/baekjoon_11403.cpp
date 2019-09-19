#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

constexpr int MAX = 101;

int N, t;

int map[MAX][MAX]; // input으로 들어오는 행렬을 저장
bool chk[MAX][MAX]; // 방문했는지 여부 체크
int ans[MAX][MAX];

// 매개변수로 정점을 받는다.
void dfs(int x) {
	for (int i = 1; i <= N; ++i) {
		if (map[x][i] == 1 && chk[x][i] == 0) {
			ans[t][i] = 1; // 시작 정점에서 i로 가는 방향이 존재
			chk[x][i] = 1;
			dfs(i);
		}
	}
}

int main()
{
	ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);

	cin >> N;
	// 방향 그래프 문제.

	for (int i = 1; i <= N; ++i) {
		for (int j = 1; j <= N; ++j) {
			cin >> map[i][j];
		}
	}

	for (int i = 1; i <= N; ++i) {
		t = i; // 한 번의 깊이 우선 탐색(dfs)이 진행될 때 시작 정점이 변하면 안되기에
		dfs(i); // 총 N개의 정점에 대해 깊이 우선 탐색(dfs)을 돌린다.
		memset(chk, 0x00, sizeof(chk));
	}

	for (int i = 1; i <= N; ++i) {
		for (int j = 1; j <= N; ++j) {
			cout << ans[i][j] << " ";
		}
		cout << "\n";
	}

	return 0;
}