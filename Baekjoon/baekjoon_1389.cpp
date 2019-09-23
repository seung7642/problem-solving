#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int N, M;
vector<int> v[101];
bool visited[101];
int depth[101];
int ans[101]; // i번 사람의 케빈 베이컨의 수

void bfs(int start) {
	queue<int> q;
	q.push(start);
	visited[start] = true;

	while (!q.empty()) {
		int x = q.front();
		q.pop();

		for (int i = 0; i < v[x].size(); i++) {
			int y = v[x][i];
			if (!visited[y]) {
				visited[y] = true;
				depth[y] = depth[x] + 1;
				q.push(y);
			}
		}
	}
}

int main(void) {
	cin >> N >> M;

	for (int i = 0, a, b; i < M; i++) {
		cin >> a >> b;
		v[a].push_back(b);
		v[b].push_back(a);
	}

	for (int i = 1; i <= N; i++) {
		int sum = 0;
		fill_n(visited, sizeof(visited), false);
		fill_n(depth, sizeof(depth) / sizeof(int), false);

		bfs(i);
		for (int j = 1; j <= N; j++)
			sum += depth[j];

		ans[i] = sum;
	}

	int min = 1000000;
	int idx;
	for (int i = 1; i <= N; i++) {
		if (min > ans[i]) {
			min = ans[i];
			idx = i;
		}
	}

	cout << idx << "\n";

	return 0;
}