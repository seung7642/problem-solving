#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int T;
vector<int> a[20001];
int color[20001]; // 인접한 정점이 서로 다른 색인지 구분하기 위해 1과 2의 값만 저장한다.

void dfs(int node, int cnt) {
	color[node] = cnt;

	for (int i = 0; i < a[node].size(); i++) {
		int next = a[node][i];

		if (color[next] == 0) {
			dfs(next, 3 - cnt);
		}
	}
}

int main(void) {
	cin >> T;

	while (T--) {
		int v, e; // V = 정점의 개수, E = 간선의 개수
		cin >> v >> e;

		// 초기화
		for (int i = 1; i <= v; i++) {
			a[i].clear();
			color[i] = false;
		}

		for (int i = 0, c, d; i < e; i++) {
			cin >> c >> d;
			a[c].push_back(d);
			a[d].push_back(c);
		}

		for (int i = 1; i <= v; i++) {
			if (color[i] == 0) {
				dfs(i, 1);
			}
		}

		bool flag = true;
		for (int i = 1; i <= v; i++) {
			for (int j = 0; j < a[i].size(); j++) {
				int idx = a[i][j];

				// i번 정점이 가지는 간선에 대해 같은 값을 가지는게 있다면 이분 그래프가 아니다.
				if (color[i] == color[idx]) flag = false;
			}
		}

		if (flag) cout << "YES\n";
		else cout << "NO\n";
	}

	return 0;
}