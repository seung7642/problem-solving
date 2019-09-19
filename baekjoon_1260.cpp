#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

constexpr int MAX_N = 1001;

int N, M, V;
vector<int> edge[MAX_N];
int chk[2][MAX_N];

int bfs(int start) {
	queue<int> q;
	q.push(start);
	chk[0][start] = 1;

	while (!q.empty()) {
		int n = q.front();
		cout << n << " ";
		q.pop();

		for (int i = 0; i < edge[n].size(); ++i) {
			if (chk[0][edge[n][i]] == 0) {
				chk[0][edge[n][i]] = 1;
				q.push(edge[n][i]);
			}
		}
	}

	return 0;
}

int dfs(int start) {
	chk[1][start] = 1;
	cout << start << " ";

	for (int i = 0; i < edge[start].size(); ++i) {
		if (chk[1][edge[start][i]] == 0) {
			dfs(edge[start][i]);
		}
	}

	return 0;
}

int main() {
	cin >> N >> M >> V;

	for (int i = 0, a, b; i < M; ++i) {
		cin >> a >> b;
		edge[a].push_back(b);
		edge[b].push_back(a);
	}

	for (int i = 0; i < N; ++i)
		sort(edge[i].begin(), edge[i].end());

	dfs(V);
	cout << "\n";
	bfs(V);

	return 0;
}