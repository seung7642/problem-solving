#include <iostream>
#include <vector>
using namespace std;

vector<int> a[1001]; // 간선을 표현할 때 (u v)로 나타내는데 벡터 변수를 v라고하면 이름 중복이된다.
bool chk[1001];

// 매개변수로 정점을 받는다.
void dfs(int x) {
	chk[x] = 1;

	for (int i = 0; i < a[x].size(); ++i) {
		int y = a[x][i];

		if (chk[y] == 0) {
			dfs(y);
		}
	}
}

int main()
{
	ios::sync_with_stdio(false); cin.tie(0); cout.tie(0);

	int n, m;
	cin >> n >> m;

	int u, v;
	for (int i = 0; i < m; ++i) {
		cin >> u >> v;
		a[u].push_back(v);
		a[v].push_back(u);
	}

	int components = 0; // 연결 요소의 갯수
	for (int i = 1; i <= n; ++i) { // 1부터 시작하는 이유는 입력을 받을때 정점을 1부터 시작한다고 뒀기때문
		// 정점의 갯수만큼 깊이 우선 탐색(dfs)을 실행한다.
		if (chk[i] == 0) {
			dfs(i);
			components++;
		}
	}

	cout << components << "\n";

	return 0;
}