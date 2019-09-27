#include <iostream>
#include <algorithm>
using namespace std;

constexpr int MAX = 15;

int L, C;
char input[MAX];
bool visited[MAX];

void dfs(int node, int depth, int n, int m) { // n은 모음, m은 자음
	if (depth == L) {
		if (n < 1 || m < 2) return;
		for (int i = 0; i < C; i++) {
			if (visited[i]) cout << input[i];
		}
		cout << "\n";
		return;
	}

	for (int i = node; i < C; i++) {
		if (!visited[i]) {
			visited[i] = true;
			int nextN = n;
			int nextM = m;

			if (input[i] == 'a' || input[i] == 'e' || input[i] == 'i' || input[i] == 'o' || input[i] == 'u')
				nextN += 1;
			else
				nextM += 1;

			dfs(i, depth + 1, nextN, nextM);
			visited[i] = false;
		}
	}
}

int main(void) {
	cin >> L >> C;

	for (int i = 0; i < C; i++) {
		cin >> input[i];
	}

	sort(input, input + C);

	dfs(0, 0, 0, 0);

	return 0;
}