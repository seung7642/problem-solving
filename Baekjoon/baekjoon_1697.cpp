#include <iostream>
#include <queue>
#include <algorithm>
using namespace std;

constexpr int MAX = 200000;

bool chk[MAX]; // i에 방문했는지의 여부 체크
int dist[MAX]; // dist[i] i까지 가는데 걸린 비용

int main() {
	cin.sync_with_stdio(false); cin.tie(NULL); cout.tie(NULL);
	int n, k;
	cin >> n >> k;

	queue<int> q;
	q.push(n);
	chk[n] = 1;
	dist[n] = 0;

	while (!q.empty()) {
		int node = q.front();
		q.pop();
		int eachCase[3] = { node - 1, node + 1, node * 2 }; // 3가지 경우로 나눠서 생각한다.

		for (int i = 0; i < 3; ++i) {
			if ((0 <= eachCase[i]) && (eachCase[i] < MAX)) {
				if (chk[eachCase[i]] == 0) {
					q.push(eachCase[i]);
					chk[eachCase[i]] = 1;
					dist[eachCase[i]] = dist[node] + 1; // 값을 축적시켜나간다.
				}
			}
		}
	}

	if (n == k)
		cout << 0 << "\n";
	else
		cout << dist[k] << "\n";

	return 0;
}