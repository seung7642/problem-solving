#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

// 화장실에서 돌아온 시간을 기준으로 정렬한다.
bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.second == b.second)
		return a.first < b.first;
	else
		return a.second < b.second;
}

int main(void) {
	// 지원자의 수와 지원자들이 화장실에 간 시간과 돌아온 시간의 목록이 주어졌을 때,
	// 모든 지원자들이 서로 다른 화장실에 들어갈 수 있는 최소 갯수
	// 그리디 알고리즘?

	int n; // 지원자의 수 <=1000
	cin >> n;

	vector<pair<int, int>> v(n);
	for (int i = 0; i < n; i++) {
		cin >> v[i].first >> v[i].second;
	}
	sort(v.begin(), v.end(), cmp);

	int toiletCount = 0;
	for (int i = 0; i < n; i++) {
		int cnt = 1;
		// i번째 사람과 동일한 시간대에 화장실을 사용한 사람이 가장 많은 수
		for (int j = i + 1; j < n; j++) {
			if (v[i].first <= v[j].first && v[i].second > v[j].first) {
				cnt++;
			}
		}
		if (toiletCount < cnt)
			toiletCount = cnt;
	}

	cout << toiletCount << "\n";

	return 0;
}