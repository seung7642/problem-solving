#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int T, N;

bool cmp(pair<int, int> a, pair<int, int> b) {
    return a.first < b.first;
}

int main() {
    ios::sync_with_stdio(false);
    cin >> T;

    // 면접시험 성적, 서류시험 성적 둘 중하나에 대해 오름차순 정렬을 한 후,
    // 나머지 하나의 시험에 대해 내림차순을 만족하는 사람은 합격
    while (T--) {
        cin >> N;
        vector<pair<int, int>> v(N);

        for (int i = 0; i < N; ++i) {
            cin >> v[i].first >> v[i].second;
        }

        sort(v.begin(), v.end(), cmp);

        int cnt = 1; // 합격자 수
        int max = v[0].second;
        for (int i = 1; i < N; ++i) {
            // 다음 사람의 성적이 바로 앞의 사람의 성적 순위보다 값이 낮아야한다.
            if (max > v[i].second) {
                cnt++;
                max = v[i].second;
            }
        }

        cout << cnt << "\n";
    }

    return 0;
}