#include <iostream>
using namespace std;

int k;
int s[14]; // 집합
int l[14]; // 로또

void dfs(int node, int depth) {
	if (depth == 7) { // 최종 깊이까지 탐색을 했다면 로또 번호 출력
		for (int i = 1; i <= 6; ++i) {
			cout << l[i] << " ";
		}
		cout << "\n";
		return;
	}

	for (int i = node; i <= k; ++i) { // 모든 노드를 하나씩 찍으며 DFS를 진행한다.
		l[depth] = s[i];
		dfs(i + 1, depth + 1);
	}
}

int main() {
	while (cin >> k && k) {
		for (int i = 1; i <= k; ++i)
			cin >> s[i];

		dfs(1, 1);
		cout << "\n";
	}

	return 0;
}