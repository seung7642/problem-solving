#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool cmp(pair<int, int> a, pair<int, int> b) {
	if (a.second == b.second)
		return a.first < b.first;
	else
		return a.second < b.second;
}

int main(void) {
	int n;
	cin >> n;

	vector<pair<int, int>> v(n);
	for (int i = 0; i < n; i++) {
		cin >> v[i].first >> v[i].second;
	}
	sort(v.begin(), v.end(), cmp);

	int toiletCount = 0;
	for (int i = 0; i < n; i++) {
		int cnt = 1;
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