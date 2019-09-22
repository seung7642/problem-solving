#include <iostream>
using namespace std;

constexpr int MAX = 20000;

int N;
bool map[MAX];

int main(void) {
	cin >> N;

	for (int i = 0; i < N; i++) {
		cin >> map[i];
	}

	int ans = 0;
	// 앉은 자리기준 좌측으로 최대값과 우측으로 최댓값을 비교한다.
	for (int i = 0; i < N; i++) {
		int maxDistance = 0;

		if (!map[i]) {
			int leftMaxDistance;
			int rightMaxDistance;
			leftMaxDistance = rightMaxDistance = 1;

			// 좌측으로 최대값
			for (int j = i - 1; j >= 0; j--) {
				if (!map[j]) leftMaxDistance += 1;
				else break;
			}

			// 우측으로 최대값
			for (int j = i + 1; j < N; j++) {
				if (!map[j]) rightMaxDistance += 1;
				else break;
			}

			// 작은 값을 담는다.
			maxDistance = leftMaxDistance > rightMaxDistance ? rightMaxDistance : leftMaxDistance;
			if (ans < maxDistance) ans = maxDistance;
		}
	}

	cout << ans << "\n";

	return 0;
}